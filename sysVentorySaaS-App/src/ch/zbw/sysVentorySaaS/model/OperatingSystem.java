package ch.zbw.sysVentorySaaS.model;

public class OperatingSystem {
	private String name;
	private String manufacturer;
	private String architecture;
	

	public OperatingSystem(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getManufacturer() {
		return manufacturer;
	}


	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}


	public String getArchitecture() {
		return architecture;
	}


	public void setArchitecture(String architecture) {
		this.architecture = architecture;
	}
	

}
