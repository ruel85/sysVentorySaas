package ch.zbw.sysVentorySaas.App.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ScanSetting{
	private int idCompany;
	private String networkName;
	private String ipStart;
	private String ipEnd;
	private int intervallHours;
	private boolean timeToScan;
	
	private Company company;
	
	public ScanSetting(String networkName, String ipStart, String ipEnd, int intervallHours, boolean timeToScan)
	{
		this.networkName=networkName;
		this.ipStart=ipStart;
		this.ipEnd=ipEnd;
		this.intervallHours=intervallHours;
		this.timeToScan=timeToScan;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public boolean getTimeToScan() {
		return timeToScan;
	}

	public void setTimeToScan(boolean timeToScan) {
		this.timeToScan = timeToScan;
	}

	public ScanSetting(){
	}

	public int getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
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
}
