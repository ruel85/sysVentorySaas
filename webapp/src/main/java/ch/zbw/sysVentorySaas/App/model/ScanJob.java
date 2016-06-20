package ch.zbw.sysVentorySaas.App.model;

public class ScanJob {

	private int idScanJob;
	private String startTime;
	private String endTime;
	private ScanStatus status;
	
	// Felder des relevanten ScanSettings
	/*private int idCompany;
	private String networkName;
	private String ipStart;
	private String ipEnd;
	private int intervallHours;
	private boolean timeToScan;*/
	
	public ScanJob(String startTime, String endTime, ScanStatus status)
	{	
		this.startTime=startTime;
		this.endTime=endTime;
		this.status=status;
	}
	
	public ScanJob()
	{	
	}

	public int getIdScanJob() {
		return idScanJob;
	}

	public void setIdScanJob(int idScanJob) {
		this.idScanJob = idScanJob;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public ScanStatus getStatus() {
		return status;
	}

	public void setStatus(ScanStatus status) {
		this.status = status;
	}
}
