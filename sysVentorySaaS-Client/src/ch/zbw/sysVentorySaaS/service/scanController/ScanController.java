package ch.zbw.sysVentorySaaS.service.scanController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import ch.zbw.sysVentorySaaS.service.ipScanner.IPScanner;
import ch.zbw.sysVentorySaaS.service.snmpscanner.SNMPScanner;
import ch.zbw.sysVentorySaas.App.model.Device;

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
		for (Device snmpDevice : snmpList) {
			Iterator<Device> it = ipList.iterator();
			while(it.hasNext()) {
				Device ipDevice = it.next();
				if(snmpDevice.getIpAddress().equals(ipDevice.getIpAddress())){
					allNetworkDevices.add(snmpDevice);
				} else {
					allNetworkDevices.add(ipDevice);
				}
			}
		}
		return allNetworkDevices;
	}
}
