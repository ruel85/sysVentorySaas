package ch.zbw.sysVentorySaas.App.model;

public class Software{
	
	private int idSoftware;
	private String name;
	private String manufacturer;
	private String version;
	
	private Device device;
	
	public Software(){
	}
	
	public Software(String name, String manufacturer, String version){
		this.name = name;
		this.manufacturer = manufacturer;
		this.version = version;
	}
	
	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public int getIdSoftware() {
		return idSoftware;
	}
	public void setIdSoftware(int idSoftware) {
		this.idSoftware = idSoftware;
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
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
