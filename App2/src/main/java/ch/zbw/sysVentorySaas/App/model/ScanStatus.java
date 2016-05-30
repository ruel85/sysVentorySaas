package ch.zbw.sysVentorySaas.App.model;

public class ScanStatus {
	
	private int idScanStatus;
	private String name;
	private String description;
	
	public ScanStatus(String name, String description){
		this.name=name;
		this.description=description;
	}
	
	public ScanStatus()
	{
	}

	public int getIdScanStatus() {
		return idScanStatus;
	}

	public void setIdScanStatus(int idScanStatus) {
		this.idScanStatus = idScanStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
