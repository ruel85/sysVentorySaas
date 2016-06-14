package ch.zbw.sysVentorySaas.App.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ScanSetting {
	
	private int idScanSetting;
	private String networkName;
	private String ipStart;
	private String ipEnd;
	private String startTime;
	private int intervallHours;
	private boolean timeToScan;
	
	public ScanSetting(String networkName, String ipStart, String ipEnd, String startTime, int intervallHours, boolean timeToScan)
	{
		this.networkName=networkName;
		this.ipStart=ipStart;
		this.ipEnd=ipEnd;
		this.startTime=startTime;
		this.intervallHours=intervallHours;
		this.timeToScan=timeToScan;
	}	
	
	public boolean getTimeToScan() {
		return timeToScan;
	}

	public void setTimeToScan(boolean timeToScan) {
		this.timeToScan = timeToScan;
	}

	public ScanSetting(){
	}

	public int getIdScanSetting() {
		return idScanSetting;
	}

	public void setIdScanSetting(int idScanSetting) {
		this.idScanSetting = idScanSetting;
	}

	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public String getIpStart() {
		return ipStart;
	}

	public void setIpStart(String ipStart) {
		this.ipStart = ipStart;
	}

	public String getIpEnd() {
		return ipEnd;
	}

	public void setIpEnd(String ipEnd) {
		this.ipEnd = ipEnd;
	}
	
	public int getIntervallHours() {
		return intervallHours;
	}

	public void setIntervallHours(int intervallHours) {
		this.intervallHours = intervallHours;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
}
