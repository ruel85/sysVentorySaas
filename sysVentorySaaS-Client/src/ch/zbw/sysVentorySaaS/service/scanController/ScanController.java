package ch.zbw.sysVentorySaaS.service.scanController;

import java.io.IOException;
import java.util.ArrayList;

import ch.zbw.sysVentorySaaS.model.Device;
import ch.zbw.sysVentorySaaS.service.ipscanner.IPScanner;
import ch.zbw.sysVentorySaaS.service.snmp.SNMPScanner;

public class ScanController {
	private ArrayList<Device> allNetworkDevices;
	private SNMPScanner snmps;
	private IPScanner ips;

	public ScanController(String ipMin, String ipMax, int pingTimeout, int snmpTimeout) {
		snmps = new SNMPScanner(ipMin, ipMax, snmpTimeout);
		ips = new IPScanner(ipMin, ipMax, pingTimeout);
	}

	public ArrayList<Device> getAllNetworkDevices() throws IOException {
		allNetworkDevices = new ArrayList<Device>();
		ArrayList<Device> snmpList = snmps.getSNMPDevices();
		ArrayList<Device> ipList = ips.getReachableIPs();
// kommt noch..
		return allNetworkDevices;
	}
}
