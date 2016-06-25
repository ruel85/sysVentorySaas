package ch.zbw.sysVentorySaas.App.model;

public class SID {
	
	private int idSID;
	private String SID;
	
	public SID(){	
	}
	
	public SID(String SID)
	{
		this.SID = SID; 
	}

	public int getIdSID() {
		return idSID;
	}

	public void setIdSID(int idSID) {
		this.idSID = idSID;
	}

	public String getSID() {
		return SID;
	}

	public void setSID(String sID) {
		SID = sID;
	}
}
