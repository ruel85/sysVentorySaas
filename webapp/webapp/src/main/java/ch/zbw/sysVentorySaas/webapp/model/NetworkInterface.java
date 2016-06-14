package ch.zbw.sysVentorySaas.webapp.model;

public class NetworkInterface {
	
	private int idNetworkInterface;
	private String name;
	
	public NetworkInterface(String name) {
		this.name=name;
	}
	
	public NetworkInterface(){
	}

	public int getIdNetworkInterface() {
		return idNetworkInterface;
	}

	public void setIdNetworkInterface(int idNetworkInterface) {
		this.idNetworkInterface = idNetworkInterface;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
