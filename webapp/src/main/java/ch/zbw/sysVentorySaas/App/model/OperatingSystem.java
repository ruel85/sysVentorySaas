package ch.zbw.sysVentorySaas.App.model;

public class OperatingSystem {
	private int idOperatingSystem;
	private String name;
//	private String manufacturer;
	private String architecture;
	
	private Device device;
	
	public OperatingSystem(String name, String architecture) {
		this.name = name;
//		this.manufacturer = manufacturer;
		this.architecture = architecture;
		this.device=device;
	}
	
	public OperatingSystem(){	
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public int getIdOperatingSystem() {
		return idOperatingSystem;
	}

	public void setIdOperatingSystem(int idOperatingSystem) {
		this.idOperatingSystem = idOperatingSystem;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public String getManufacturer() {
//		return manufacturer;
//	}
//
//	public void setManufacturer(String manufacturer) {
//		this.manufacturer = manufacturer;
//	}

	public String getArchitecture() {
		return architecture;
	}

	public void setArchitecture(String architecture) {
		this.architecture = architecture;
	}
}
