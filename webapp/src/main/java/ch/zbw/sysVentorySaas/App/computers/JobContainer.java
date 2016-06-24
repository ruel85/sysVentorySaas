package ch.zbw.sysVentorySaas.App.computers;

import java.util.ArrayList;
import java.util.List;

import ch.zbw.sysVentorySaas.App.computers.Computers.Computer.Prozessoren.Prozessor;
import ch.zbw.sysVentorySaas.App.model.Device;
import ch.zbw.sysVentorySaas.App.model.NetworkInterface;
import ch.zbw.sysVentorySaas.App.model.OperatingSystem;
import ch.zbw.sysVentorySaas.App.model.Printer;
import ch.zbw.sysVentorySaas.App.model.Software;

public class JobContainer implements IScanJobContainer {

	private Device device;
	private ArrayList<Software> softwareList;
	private OperatingSystem operatingSystem;
	private Prozessor prozessor;
	private Printer printer;
	private NetworkInterface networkinterface;
	
	public JobContainer() {
		softwareList = new ArrayList<Software>();
	}

	public ArrayList<Software> getSoftwareList() {
		return softwareList;
	}

	public void setSoftwareList(ArrayList<Software> softwareList) {
		this.softwareList = softwareList;
	}

	@Override
	public Device getDevice() {
		return this.device;
	}

	@Override
	public OperatingSystem getOperatingSystem() {
		return this.operatingSystem;
	}

	@Override
	public Prozessor getProzessor() {
		return this.prozessor;
	}

	@Override
	public Printer getPrinter() {
		return this.printer;
	}

	@Override
	public NetworkInterface getNetworkInterface() {
		return this.networkinterface;
	}

	public NetworkInterface getNetworkinterface() {
		return networkinterface;
	}

	public void setNetworkinterface(NetworkInterface networkinterface) {
		this.networkinterface = networkinterface;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public void setOperatingSystem(OperatingSystem operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public void setProzessor(Prozessor prozessor) {
		this.prozessor = prozessor;
	}

	public void setPrinter(Printer printer) {
		this.printer = printer;
	}	
}
