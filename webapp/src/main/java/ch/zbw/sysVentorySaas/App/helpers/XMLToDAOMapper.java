package ch.zbw.sysVentorySaas.App.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import ch.zbw.sysVentorySaas.App.DataAccessObject.DeviceDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.NetworkInterfaceDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.OperatingSystemDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.PrinterDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ProcessorDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ScanJobDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.SoftwareDAO;
import ch.zbw.sysVentorySaas.App.computers.Computers;
import ch.zbw.sysVentorySaas.App.computers.Computers.Computer;
import ch.zbw.sysVentorySaas.App.computers.Computers.Computer.Netzwerkkonfiguration.NetzwerkKarte;
import ch.zbw.sysVentorySaas.App.computers.Computers.Computer.Peripherie.Druckers;
import ch.zbw.sysVentorySaas.App.computers.Computers.Computer.Peripherie.Druckers.Drucker;
import ch.zbw.sysVentorySaas.App.computers.Computers.Computer.Programme.Programm;
import ch.zbw.sysVentorySaas.App.computers.Computers.Computer.Prozessoren.Prozessor;
import ch.zbw.sysVentorySaas.App.computers.ObjectFactory;
import ch.zbw.sysVentorySaas.App.model.Device;
import ch.zbw.sysVentorySaas.App.model.NetworkInterface;
import ch.zbw.sysVentorySaas.App.model.OperatingSystem;
import ch.zbw.sysVentorySaas.App.model.Printer;
import ch.zbw.sysVentorySaas.App.model.Processor;
import ch.zbw.sysVentorySaas.App.model.ScanJob;
import ch.zbw.sysVentorySaas.App.model.Software;

public class XMLToDAOMapper {

	public static void importData(InputStream i) throws Throwable
	{
		Device newDevice;
		OperatingSystem newOperatingSystem;
		Software software;
		Processor newProcessor;
		Printer newPrinter;
		NetworkInterface newNetworkInterface;
		ScanJob newScanJob;
		
		final JAXBContext jaxbContext= JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller jaxbUnmarshaller=jaxbContext.createUnmarshaller();
				
		InputStream is = i;
		
		Computers comp = (Computers) jaxbUnmarshaller.unmarshal(is);
		System.out.println("Anzahl Computer:" + comp.getComputer().size());		
		
		List<Computer> comps = comp.getComputer();		
		if(comps == null || comps.size() == 0)
			throw new Throwable("Keine Computers vorhanden!");

		newScanJob = new ScanJob();
		newScanJob.setStartTime(comp.getStamp());
		ScanJobDAO.createScanJob(newScanJob);
		
		for(Computer oneComp : comps)
		{
			newDevice = new Device();
			newDevice.setMacAddress(oneComp.getSystem().getComputername().getValue());
			newDevice.setName(oneComp.getSIDS().getSID().get(0).getValue());
			newDevice.setIpAddress(oneComp
					.getNetzwerkkonfiguration().getNetzwerkKarte().get(0).getIPAdresse().getValue());
			newDevice.setManufacturer("Hersteller unbekannt");
			DeviceDAO.createDevice(newDevice);
			
			newOperatingSystem = new OperatingSystem();
			newOperatingSystem.setName(oneComp.getSystem().getBetriebssystem().getValue());
			newOperatingSystem.setManufacturer("Hersteller unbekannt");
			newOperatingSystem.setArchitecture(oneComp.getSystem().getOSArchitektur().getValue());
			OperatingSystemDAO.createOperatingSystem(newOperatingSystem);
			
			newProcessor = new Processor();
			newProcessor.setName(oneComp.getProzessoren().getProzessor().getName().getValue());
			newProcessor.setCores(oneComp.getProzessoren().getProzessor().getNoC().getValue());
			newProcessor.setFrequency(oneComp.getProzessoren().getProzessor().getCCS().getValue().toString());
			ProcessorDAO.createProcessor(newProcessor);
			
			List<Drucker> druckerList = oneComp.getPeripherie().getDruckers().getDrucker();
			for(Drucker oneDrucker :druckerList)
			{
				newPrinter = new Printer();
				newPrinter.setName(oneDrucker.getValue());
				newPrinter.setDescription("Keine Beschreibung vorhanden.");
				PrinterDAO.createPrinter(newPrinter);
			}

			List<NetzwerkKarte> netzwerkKartenListe = oneComp.getNetzwerkkonfiguration().getNetzwerkKarte();
			for(NetzwerkKarte oneNetzwerkKarte :netzwerkKartenListe)
			{
				newNetworkInterface = new NetworkInterface();
				newNetworkInterface.setName(oneNetzwerkKarte.getBeschreibung().getValue());
				NetworkInterfaceDAO.createNetworkInterface(newNetworkInterface);
			}
			
			List<Programm> programmList = oneComp.getProgramme().getProgramm();
			for(Programm oneProgramm :programmList)
			{
				software = new Software();
				software.setManufacturer("Hersteller unbekannt");
				software.setName(oneProgramm.getName().getValue());
				software.setVersion(oneProgramm.getVersion().getValue());
				SoftwareDAO.createSoftware(software);
			}
			
		}
	}
}
