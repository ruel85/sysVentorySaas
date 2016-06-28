package ch.zbw.sysVentorySaas.App.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Timestamp;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import ch.zbw.sysVentorySaas.App.DataAccessObject.DeviceDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.NetworkInterfaceDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.OperatingSystemDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.PrinterDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.PrinterDriverDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ProcessorDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ScanJobDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ScanSettingDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.SoftwareDAO;
import ch.zbw.sysVentorySaas.App.computers.Computers;
import ch.zbw.sysVentorySaas.App.computers.Computers.Computer;
import ch.zbw.sysVentorySaas.App.computers.Computers.Computer.Netzwerkkonfiguration.NetzwerkKarte;
import ch.zbw.sysVentorySaas.App.computers.Computers.Computer.Peripherie.Druckers;
import ch.zbw.sysVentorySaas.App.computers.Computers.Computer.Peripherie.Druckers.Drucker;
import ch.zbw.sysVentorySaas.App.computers.Computers.Computer.Peripherie.Druckertreibers.Druckertreiber;
import ch.zbw.sysVentorySaas.App.computers.Computers.Computer.Programme.Programm;
import ch.zbw.sysVentorySaas.App.computers.Computers.Computer.Prozessoren.Prozessor;
import ch.zbw.sysVentorySaas.App.computers.Computers.Computer.SIDS.SID;
import ch.zbw.sysVentorySaas.App.computers.ObjectFactory;
import ch.zbw.sysVentorySaas.App.model.Device;
import ch.zbw.sysVentorySaas.App.model.NetworkInterface;
import ch.zbw.sysVentorySaas.App.model.OperatingSystem;
import ch.zbw.sysVentorySaas.App.model.Printer;
import ch.zbw.sysVentorySaas.App.model.PrinterDriver;
import ch.zbw.sysVentorySaas.App.model.Processor;
import ch.zbw.sysVentorySaas.App.model.ScanJob;
import ch.zbw.sysVentorySaas.App.model.ScanSetting;
import ch.zbw.sysVentorySaas.App.model.Software;

public class XMLToDAOMapper {

	public static void importData(InputStream i, ScanSetting scanSetting) throws Throwable
	{
		Device newDevice;
		OperatingSystem newOperatingSystem;
		Software software;
		Processor newProcessor;
		Printer newPrinter;
		PrinterDriver newPrinterDriver;
		NetworkInterface newNetworkInterface;
		ScanJob newScanJob;
		ch.zbw.sysVentorySaas.App.model.SID sid;
		
		final JAXBContext jaxbContext= JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller jaxbUnmarshaller=jaxbContext.createUnmarshaller();
		
		DateTimeFormatter format = DateTimeFormatter
		        .ofPattern("d MMM yyyy  hh:mm a");
		ZoneId zone = ZoneId.of("Europe/Paris");
		LocalDateTime dateTime = LocalDateTime.of(LocalDate.now(zone), LocalTime.now(zone));
		
		if(scanSetting != null && scanSetting.getIdCompany() != 0)
		{
			scanSetting.setTimeToScan(false);
			ScanSettingDAO.saveScanSetting(scanSetting);
		}
		
		newScanJob = new ScanJob(dateTime.format(format), "", JobStatus.Erstellt
				, scanSetting.getNetworkName(), scanSetting.getIpStart(), scanSetting.getIpEnd(), scanSetting.getIntervallMinutes(),scanSetting);
		newScanJob = ScanJobDAO.saveScanJob(newScanJob);
		
		if(i == null)
			throw new Exception("Kein XML-Daten vorhanden!");
		
		Computers comp = (Computers) jaxbUnmarshaller.unmarshal(i);	
		List<Computer> comps = comp.getComputer();		
		if(comps == null || comps.size() == 0)
			throw new Throwable("Keine Computers vorhanden!");
		
		try {
			newScanJob.setJobStatus(JobStatus.InVerarbeitung);
			newScanJob = ScanJobDAO.saveScanJob(newScanJob);
			for(Computer oneComp : comps)
			{
				newDevice = new Device();
				newDevice.setMacAddress(oneComp.getNetzwerkkonfiguration().getNetzwerkKarte().get(0).getMacadresse().getValue());
				newDevice.setName(oneComp.getSystem().getComputername().getValue());
				newDevice.setIpAddress(oneComp
						.getNetzwerkkonfiguration().getNetzwerkKarte().get(0).getIPAdresse().getValue());
				newDevice.setManufacturer("Keine Angaben vorhanden");
				newDevice.setMemory(oneComp.getSystem().getMemory().getValue().toString());
				newDevice.setSystemType(oneComp.getSystem().getSystemtype().getValue());
				newDevice.setScanJob(newScanJob);
				newDevice = DeviceDAO.saveDevice(newDevice);
				
				newOperatingSystem = new OperatingSystem();
				newOperatingSystem.setName(oneComp.getSystem().getBetriebssystem().getValue());
				//newOperatingSystem.setManufacturer("Hersteller unbekannt");
				newOperatingSystem.setArchitecture(oneComp.getSystem().getOSArchitektur().getValue());
				newOperatingSystem.setDevice(newDevice);
				OperatingSystemDAO.saveOperatingSystem(newOperatingSystem);
				
				newProcessor = new Processor();
				newProcessor.setName(oneComp.getProzessoren().getProzessor().getName().getValue());
				newProcessor.setCores(oneComp.getProzessoren().getProzessor().getNoC().getValue());
				newProcessor.setFrequency(oneComp.getProzessoren().getProzessor().getCCS().getValue().toString());
				newProcessor.setDevice(newDevice);
				ProcessorDAO.saveProcessor(newProcessor);
				
				List<Drucker> druckerList = oneComp.getPeripherie().getDruckers().getDrucker();
				for(Drucker oneDrucker :druckerList)
				{
					newPrinter = new Printer();
					newPrinter.setName(oneDrucker.getValue());
					newPrinter.setDescription("Keine Beschreibung vorhanden.");
					newPrinter.setDevice(newDevice);
					PrinterDAO.savePrinter(newPrinter);
				}
				
				List<Druckertreiber> druckertreiberList = oneComp.getPeripherie().getDruckertreibers().getDruckertreiber();
				for(Druckertreiber oneDruckertreiber :druckertreiberList)
				{
					newPrinterDriver = new PrinterDriver();
					newPrinterDriver.setName(oneDruckertreiber.getValue());
					newPrinterDriver.setDevice(newDevice);
					PrinterDriverDAO.savePrinterDriver(newPrinterDriver);
				}

				List<NetzwerkKarte> netzwerkKartenListe = oneComp.getNetzwerkkonfiguration().getNetzwerkKarte();
				for(NetzwerkKarte oneNetzwerkKarte :netzwerkKartenListe)
				{
					newNetworkInterface = new NetworkInterface();
					newNetworkInterface.setName(oneNetzwerkKarte.getBeschreibung().getValue());
					newNetworkInterface.setDhcp(oneNetzwerkKarte.getDHCP().getValue());
					newNetworkInterface.setSubnet(oneNetzwerkKarte.getSubnetz().getValue());
					newNetworkInterface.setGateway(oneNetzwerkKarte.getGateway().getValue());
					newNetworkInterface.setMacAddress(oneNetzwerkKarte.getMacadresse().getValue());
					newNetworkInterface.setDevice(newDevice);
					NetworkInterfaceDAO.saveNetworkInterface(newNetworkInterface);
				}
				
				List<Programm> programmList = oneComp.getProgramme().getProgramm();
				for(Programm oneProgramm :programmList)
				{
					software = new Software();
					//software.setManufacturer("Hersteller unbekannt");
					software.setName(oneProgramm.getName().getValue());
					software.setVersion(oneProgramm.getVersion().getValue());
					software.setDevice(newDevice);
					SoftwareDAO.saveSoftware(software);
				}
				
				List<SID> sidList = oneComp.getSIDS().getSID();
				for(SID oneSID : sidList)
				{
					sid = new ch.zbw.sysVentorySaas.App.model.SID();
					sid.setDevice(newDevice);
					sid.setSID(oneSID.getValue());				
				}
			}
			
			newScanJob.setEndTime(dateTime.format(format));
			newScanJob.setJobStatus(JobStatus.Erledigt);
			ScanJobDAO.saveScanJob(newScanJob);
		
		} catch (Exception e) {
			newScanJob.setEndTime(dateTime.format(format));
			newScanJob.setJobStatus(JobStatus.Fehler);
			ScanJobDAO.saveScanJob(newScanJob);
			throw new Exception("Fehler beim Einlesen des XML-Results.");
		}
	}
}
