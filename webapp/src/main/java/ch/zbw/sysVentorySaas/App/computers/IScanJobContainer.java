package ch.zbw.sysVentorySaas.App.computers;

import ch.zbw.sysVentorySaas.App.computers.Computers.Computer.Prozessoren.Prozessor;
import ch.zbw.sysVentorySaas.App.model.Device;
import ch.zbw.sysVentorySaas.App.model.NetworkInterface;
import ch.zbw.sysVentorySaas.App.model.OperatingSystem;
import ch.zbw.sysVentorySaas.App.model.Printer;

public interface IScanJobContainer {
	
	public Device getDevice();
	public OperatingSystem getOperatingSystem();
	public Prozessor getProzessor();
	public Printer getPrinter();
	public NetworkInterface getNetworkInterface();
}
