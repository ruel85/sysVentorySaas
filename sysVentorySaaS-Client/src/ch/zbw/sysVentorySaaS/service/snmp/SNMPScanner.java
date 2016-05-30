package ch.zbw.sysVentorySaaS.service.snmp;

import java.io.IOException;
import java.util.ArrayList;
import org.snmp4j.smi.OID;

import ch.zbw.sysVentorySaas.App.model.Device;
import net.ripe.commons.ip.Ipv4;
import net.ripe.commons.ip.Ipv4Range;

public class SNMPScanner {
	private String ipMin;
	private String ipMax;
	private int pingTime;

	public SNMPScanner(String ipMin, String ipMax, int pingTime) {
		this.ipMin = ipMin;
		this.ipMax = ipMax;
		this.pingTime = pingTime;
	}

	public ArrayList<Device> getSNMPDevices() throws IOException {
		ArrayList<String> ipList = new ArrayList<>();
		for (Ipv4 ipv4 : Ipv4Range.parse(ipMin + "-" + ipMax)) {
			ipList.add(ipv4.toString());
		}
		ArrayList<Thread> threads = new ArrayList<>();
		ArrayList<SNMPThread> snmpThreads = new ArrayList<>();
		ArrayList<Device> snmpDevices = new ArrayList<>();
		for (String ipv4 : ipList) {
			SNMPThread st = new SNMPThread(ipv4, pingTime);
			snmpThreads.add(st);
			Thread t = new Thread(st);
			threads.add(t);
			t.start();
		}
		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (SNMPThread st : snmpThreads) {
			if (st.isReachable()) {
				String macAdressIndex = st.getAsString(new OID(".1.3.6.1.2.1.4.20.1.2." + st.getIpv4()));
				String macAdress = st.getAsString(new OID(".1.3.6.1.2.1.2.2.1.6." + macAdressIndex));
				Device snmpDevice = new Device(macAdress);
				String deviceName = st.getAsString(new OID(".1.3.6.1.2.1.1.5.0"));
				snmpDevice.setIpAddress(st.getIpv4());
				snmpDevice.setName(deviceName);
				snmpDevices.add(snmpDevice);
			}
		}
		return snmpDevices;
	}

}
