package ch.zbw.sysVentorySaaS.service.scanController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import ch.zbw.sysVentorySaaS.service.ipScanner.IPScanner;
import ch.zbw.sysVentorySaaS.service.snmpscanner.SNMPScanner;
import ch.zbw.sysVentorySaas.App.model.Device;

/**
 * ScanController ist der Controller f�r die isReachable- und SNMP-Scans
 * 
 * @author Damjan Djuranovic
 *
 */
public class ScanController {
	private ArrayList<Device> allNetworkDevices;
	private SNMPScanner snmps;
	private IPScanner ips;

	/**
	 * @param ipMin
	 * @param ipMax
	 * @param pingTimeout
	 * @param snmpTimeout
	 */
	public ScanController(String ipMin, String ipMax, int pingTimeout, int snmpTimeout) {
		snmps = new SNMPScanner(ipMin, ipMax, snmpTimeout);
		ips = new IPScanner(ipMin, ipMax, pingTimeout);
	}

	/**
	 * Gib alle Netwerkger�te zur�ck die gefunden wordne sind, priorisiert dabei
	 * SNMP-Ger�te
	 * 
	 * @return
	 * @throws IOException
	 */
	public ArrayList<Device> getAllNetworkDevices() throws IOException {
		allNetworkDevices = new ArrayList<Device>();
		ArrayList<Device> snmpList = snmps.getSNMPDevices();
		ArrayList<Device> ipList = ips.getReachableIPs();
		for (Device snmpDevice : snmpList) {
			Iterator<Device> it = ipList.iterator();
			while (it.hasNext()) {
				Device ipDevice = it.next();
				if (snmpDevice.getIpAddress().equals(ipDevice.getIpAddress())) {
					allNetworkDevices.add(snmpDevice);
				} else {
					allNetworkDevices.add(ipDevice);
				}
			}
		}
		return allNetworkDevices;
	}

	/**
	 * @return
	 */
	public SNMPScanner getSnmps() {
		return snmps;
	}

	/**
	 * @param snmps
	 */
	public void setSnmps(SNMPScanner snmps) {
		this.snmps = snmps;
	}

	/**
	 * @return
	 */
	public IPScanner getIps() {
		return ips;
	}

	/**
	 * @param ips
	 */
	public void setIps(IPScanner ips) {
		this.ips = ips;
	}

	/**
	 * @param allNetworkDevices
	 */
	public void setAllNetworkDevices(ArrayList<Device> allNetworkDevices) {
		this.allNetworkDevices = allNetworkDevices;
	}

	@Override
	public String toString() {
		return "ScanController [allNetworkDevices=" + allNetworkDevices + ", snmps=" + snmps + ", ips=" + ips + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allNetworkDevices == null) ? 0 : allNetworkDevices.hashCode());
		result = prime * result + ((ips == null) ? 0 : ips.hashCode());
		result = prime * result + ((snmps == null) ? 0 : snmps.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScanController other = (ScanController) obj;
		if (allNetworkDevices == null) {
			if (other.allNetworkDevices != null)
				return false;
		} else if (!allNetworkDevices.equals(other.allNetworkDevices))
			return false;
		if (ips == null) {
			if (other.ips != null)
				return false;
		} else if (!ips.equals(other.ips))
			return false;
		if (snmps == null) {
			if (other.snmps != null)
				return false;
		} else if (!snmps.equals(other.snmps))
			return false;
		return true;
	}

}
