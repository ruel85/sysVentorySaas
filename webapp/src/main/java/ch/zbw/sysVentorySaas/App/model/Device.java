package ch.zbw.sysVentorySaas.App.model;

import java.util.Set;

public class Device {
	private int idDevice;
	private String name;
	private String macAddress;
	private String ipAddress;
	private String manufacturer;
	private String memory;
	private String systemtype;
	
	private ScanJob scanJob;
	
	private Set<Software> software;
	private Set<OperatingSystem> operatingSystem;
	private Set<SID> sid;
	private Set<Processor> processors;
	private Set<Printer> printers;
	private Set<NetworkInterface> networkInterfaces;
	private Set<PrinterDriver> printerDrivers;

	public Device(String name, String manufacturer, String macAddress, String ipAddress, String memory, String systemtype) {
		this.name=name;
		this.manufacturer=manufacturer;
		this.macAddress=macAddress;
		this.ipAddress=ipAddress;
		this.memory = memory;
		this.systemtype = systemtype;
	}
	
	public Device()
	{
	}

	public Set<PrinterDriver> getPrinterDrivers() {
		return printerDrivers;
	}

	public void setPrinterDrivers(Set<PrinterDriver> printerDrivers) {
		this.printerDrivers = printerDrivers;
	}

	public Set<Processor> getProcessors() {
		return processors;
	}

	public void setProcessors(Set<Processor> processors) {
		this.processors = processors;
	}

	public Set<Printer> getPrinters() {
		return printers;
	}

	public void setPrinters(Set<Printer> printers) {
		this.printers = printers;
	}

	public Set<NetworkInterface> getNetworkInterfaces() {
		return networkInterfaces;
	}

	public void setNetworkInterfaces(Set<NetworkInterface> networkInterfaces) {
		this.networkInterfaces = networkInterfaces;
	}

	public Set<SID> getSid() {
		return sid;
	}

	public void setSid(Set<SID> sid) {
		this.sid = sid;
	}

	public Set<OperatingSystem> getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(Set<OperatingSystem> operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public ScanJob getScanJob() {
		return scanJob;
	}

	public void setScanJob(ScanJob scanJob) {
		this.scanJob = scanJob;
	}

	public String getSystemtype() {
		return systemtype;
	}

	public void setSystemtype(String systemtype) {
		this.systemtype = systemtype;
	}

	public Set<Software> getSoftware() {
		return software;
	}

	public void setSoftware(Set<Software> software) {
		this.software = software;
	}

	public String getSystemType() {
		return systemtype;
	}

	public void setSystemType(String systemType) {
		this.systemtype = systemType;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public int getIdDevice() {
		return idDevice;
	}

	public void setIdDevice(int idDevice) {
		this.idDevice = idDevice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getMacAddress() {
		return macAddress;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Override
	public String toString() {
		return "Device [name=" + name + ", macAdress=" + macAddress + ", ipAdress=" + ipAddress + ", manufacturer="
				+ manufacturer + "]";
	}

}
