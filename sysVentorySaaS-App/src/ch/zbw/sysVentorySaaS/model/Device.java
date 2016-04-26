package ch.zbw.sysVentorySaaS.model;

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

}
