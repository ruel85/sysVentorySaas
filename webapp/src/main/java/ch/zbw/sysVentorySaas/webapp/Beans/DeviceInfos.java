package ch.zbw.sysVentorySaas.webapp.Beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import ch.zbw.sysVentorySaas.App.DataAccessObject.DeviceDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.NetworkInterfaceDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.OperatingSystemDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.PrinterDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.PrinterDriverDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ProcessorDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.SIDDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.SoftwareDAO;
import ch.zbw.sysVentorySaas.App.model.Device;
import ch.zbw.sysVentorySaas.App.model.NetworkInterface;
import ch.zbw.sysVentorySaas.App.model.OperatingSystem;
import ch.zbw.sysVentorySaas.App.model.Printer;
import ch.zbw.sysVentorySaas.App.model.PrinterDriver;
import ch.zbw.sysVentorySaas.App.model.Processor;
import ch.zbw.sysVentorySaas.App.model.SID;
import ch.zbw.sysVentorySaas.App.model.Software;

@ManagedBean
@SessionScoped
public class DeviceInfos implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;

	private int idDevice;

	private List<NetworkInterface> interfaces;
	private List<OperatingSystem> operatingSystem;
	private List<Printer> printers;
	private List<PrinterDriver> printerDrivers;
	private List<Processor> processor;
	private List<SID> sid;
	private List<Software> software;

	public String getDeviceInfosByDevice(int idDevice) {
		this.setIdDevice(idDevice);
		this.setInterfaces(NetworkInterfaceDAO.getNetworkInterfacesByDeviceId(idDevice));
		this.setOperatingSystem(OperatingSystemDAO.getOperatingSystemByIdDevice(idDevice));
		this.setPrinters(PrinterDAO.getPrinterByIdDevice(idDevice));
		this.setPrinterDrivers(PrinterDriverDAO.getPrinterDriverByIdDevice(idDevice));
		this.setProcessor(ProcessorDAO.getProcessorByIdDevice(idDevice));
		this.setSid(SIDDAO.getSIDByIdDevice(idDevice));
		this.setSoftware(SoftwareDAO.getSoftwareByIdDevice(idDevice));
		return "DeviceInfos";
	}

	public int getIdDevice() {
		return idDevice;
	}

	public void setIdDevice(int idDevice) {
		this.idDevice = idDevice;
	}

	public List<OperatingSystem> getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(List<OperatingSystem> operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public List<Printer> getPrinters() {
		return printers;
	}

	public void setPrinters(List<Printer> printers) {
		this.printers = printers;
	}

	public List<PrinterDriver> getPrinterDrivers() {
		return printerDrivers;
	}

	public void setPrinterDrivers(List<PrinterDriver> printerDrivers) {
		this.printerDrivers = printerDrivers;
	}

	public List<Processor> getProcessor() {
		return processor;
	}

	public void setProcessor(List<Processor> processor) {
		this.processor = processor;
	}

	public List<SID> getSid() {
		return sid;
	}

	public void setSid(List<SID> sid) {
		this.sid = sid;
	}

	public List<Software> getSoftware() {
		return software;
	}

	public void setSoftware(List<Software> software) {
		this.software = software;
	}

	public List<NetworkInterface> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<NetworkInterface> interfaces) {
		this.interfaces = interfaces;
	}
}
