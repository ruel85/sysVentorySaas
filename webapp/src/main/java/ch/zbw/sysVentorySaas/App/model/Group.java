package ch.zbw.sysVentorySaas.App.model;

public class Group {

	private int idGroup;
	private String name;
	
	
	
	public Group(String name) {
		this.name=name;
	}
	
	public Group(){
	}

	public int getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(int idGroup) {
		this.idGroup = idGroup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
