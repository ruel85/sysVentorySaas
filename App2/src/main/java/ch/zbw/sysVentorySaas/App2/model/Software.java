package ch.zbw.sysVentorySaas.App2.model;

import java.io.Serializable;

public class Software implements Serializable{
	
	private int idSoftware;
	private String softwareName;
	private String manufacturer;
	private String version;
	
	public Software(){
		
	}
	
	public Software(String name, String manufacturer, String version){
		this.softwareName = name;
		this.manufacturer = manufacturer;
		this.version = version;
	}
	
	public int getIdSoftware() {
		return idSoftware;
	}
	public void setIdSoftware(int idSoftware) {
		this.idSoftware = idSoftware;
	}
	
	public String getSoftwareName() {
		return softwareName;
	}
	public void setSoftwareName(String name) {
		this.softwareName = name;
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
