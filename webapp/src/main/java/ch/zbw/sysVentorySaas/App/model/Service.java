package ch.zbw.sysVentorySaas.App.model;

public class Service {
	
	private int idService;
	private String name;
	private boolean enabled;
	
	public Service(String name, boolean enabled) {
		this.name=name;
		this.enabled=enabled;
	}
	
	public Service(){
	}

	public int getIdService() {
		return idService;
	}

	public void setIdService(int idService) {
		this.idService = idService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
