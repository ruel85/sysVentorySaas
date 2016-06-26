package ch.zbw.sysVentorySaas.App.model;

import ch.zbw.sysVentorySaas.App.helpers.JobStatus;

public class ScanJob {

	private int idScanJob;
	private String startTime;
	private String endTime;
	private JobStatus jobStatus;
	
	private ScanSetting scanSetting;
	
	// Felder des relevanten ScanSettings, damit die urspr. Konfig nicht verloren geht...
	private int idCompany_Copie;
	private String networkName_Copie;
	private String ipStart_Copie;
	private String ipEnd_Copie;
	private int intervallMinutes_copie;
	
	public ScanJob(String startTime, String endTime, JobStatus jobStatus
			,String networkName_Copie, String ipStart_Copie ,String ipEnd_Copie, int intervallMinutes_copie, ScanSetting scanSetting)
	{	
		this.startTime=startTime;
		this.endTime=endTime;
		this.jobStatus=jobStatus;
		this.scanSetting=scanSetting;
		
		this.networkName_Copie = networkName_Copie;
		this.ipStart_Copie = ipStart_Copie;
		this.ipEnd_Copie = ipEnd_Copie;
		this.intervallMinutes_copie = intervallMinutes_copie;		
	}
	
	public ScanJob()
	{	
	}

	public ScanSetting getScanSetting() {
		return scanSetting;
	}

	public void setScanSetting(ScanSetting scanSetting) {
		this.scanSetting = scanSetting;
	}

	public String getNetworkName_Copie() {
		return networkName_Copie;
	}

	public void setNetworkName_Copie(String networkName_Copie) {
		this.networkName_Copie = networkName_Copie;
	}

	public String getIpStart_Copie() {
		return ipStart_Copie;
	}

	public void setIpStart_Copie(String ipStart_Copie) {
		this.ipStart_Copie = ipStart_Copie;
	}

	public String getIpEnd_Copie() {
		return ipEnd_Copie;
	}

	public void setIpEnd_Copie(String ipEnd_Copie) {
		this.ipEnd_Copie = ipEnd_Copie;
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

	public JobStatus getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(JobStatus jobStatus) {
		this.jobStatus = jobStatus;
	}

	public int getIntervallMinutes_copie() {
		return intervallMinutes_copie;
	}

	public void setIntervallMinutes_copie(int intervallMinutes_copie) {
		this.intervallMinutes_copie = intervallMinutes_copie;
	}
}
