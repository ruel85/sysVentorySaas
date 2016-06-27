package ch.zbw.sysVentorySaas.App.model;

public class NetworkInterface {
	
	private int idNetworkInterface;
	private String name;
	private String dhcp;
	private String subnet;
	private String gateway;
	private String macAddress;
	
	private Device device;
	
	public NetworkInterface(){
	}
	
	public NetworkInterface(String name, String dhcp, String subnet, String gateway, String macAddress){
		this.name=name;
		this.dhcp=dhcp;
		this.subnet=subnet;
		this.gateway=gateway;
		this.macAddress=macAddress;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public String getDhcp() {
		return dhcp;
	}

	public void setDhcp(String dhcp) {
		this.dhcp = dhcp;
	}

	public String getSubnet() {
		return subnet;
	}

	public void setSubnet(String subnet) {
		this.subnet = subnet;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
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
