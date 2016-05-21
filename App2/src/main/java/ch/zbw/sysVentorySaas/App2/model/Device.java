package ch.zbw.sysVentorySaas.App2.model;

public class Device {
	private String name;
	private String macAdress;
	private String ipAdress;
	private String manufacturer;


	public Device(String macAdress) {
		this.macAdress = macAdress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public String getMacAdress() {
		return macAdress;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Override
	public String toString() {
		return "Device [name=" + name + ", macAdress=" + macAdress + ", ipAdress=" + ipAdress + ", manufacturer="
				+ manufacturer + "]";
	}

}
