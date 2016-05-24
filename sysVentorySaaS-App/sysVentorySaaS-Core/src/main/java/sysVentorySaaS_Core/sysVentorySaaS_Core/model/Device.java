package sysVentorySaaS_Core.sysVentorySaaS_Core.model;

public class Device {
	private int idDevice;
	private String name;
	private String macAddress;
	private String ipAddress;
	private String manufacturer;

	public Device(String macAddress) {
		this.macAddress = macAddress;
	}

	public Device(String name, String manufacturer, String macAddress, String ipAddress) {
		this.name=name;
		this.manufacturer=manufacturer;
		this.macAddress=macAddress;
		this.ipAddress=ipAddress;
	}
	
	public Device()
	{
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
