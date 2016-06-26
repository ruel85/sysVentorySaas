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
	
	private Set<Software> software;

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
