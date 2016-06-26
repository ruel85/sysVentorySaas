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
	private int intervallHours_Copie;
	
	public ScanJob(String startTime, String endTime, JobStatus jobStatus, ScanSetting scanSetting)
	{	
		this.startTime=startTime;
		this.endTime=endTime;
		this.jobStatus=jobStatus;
		this.scanSetting=scanSetting;
		
		this.idCompany_Copie = scanSetting.getIdCompany();
		this.networkName_Copie = scanSetting.getNetworkName();
		this.ipStart_Copie = scanSetting.getIpStart();
		this.ipEnd_Copie = scanSetting.getIpEnd();
		this.intervallHours_Copie = scanSetting.getIntervallHours();		
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

	public int getIdCompany_Copie() {
		return idCompany_Copie;
	}

	public void setIdCompany_Copie(int idCompany_Copie) {
		this.idCompany_Copie = idCompany_Copie;
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

	public int getIntervallHours_Copie() {
		return intervallHours_Copie;
	}

	public void setIntervallHours_Copie(int intervallHours_Copie) {
		this.intervallHours_Copie = intervallHours_Copie;
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
}
