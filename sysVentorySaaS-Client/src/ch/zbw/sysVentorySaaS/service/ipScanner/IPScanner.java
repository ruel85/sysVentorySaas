package ch.zbw.sysVentorySaaS.service.ipScanner;

import java.util.ArrayList;

import ch.zbw.sysVentorySaas.App.model.Device;
import net.ripe.commons.ip.Ipv4;
import net.ripe.commons.ip.Ipv4Range;

public class IPScanner {
	private String ipMin;
	private String ipMax;
	private int pingTime;

	public IPScanner(String ipMin, String ipMax, int pingTime) {
		this.ipMin = ipMin;
		this.ipMax = ipMax;
		this.pingTime = pingTime;
	}

	public ArrayList<Device> getReachableIPs() {
		ArrayList<String> ipList = new ArrayList<>();
		for (Ipv4 ipv4 : Ipv4Range.parse(ipMin + "-" + ipMax)) {
			ipList.add(ipv4.toString());
		}
		ArrayList<Thread> threads = new ArrayList<>();
		ArrayList<PingThread> pingThreads = new ArrayList<>();
		ArrayList<Device>  onlineIPs = new ArrayList<>();
		for (String ipv4 : ipList) {
			PingThread pt = new PingThread(ipv4, pingTime);
			pingThreads.add(pt);
			Thread t = new Thread(pt);
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
		for (PingThread pt : pingThreads) {
			if (pt.isReachable()) {
				Device d = new Device(pt.getMacAddress());
				d.setIpAddress(pt.getIpv4());
				d.setName(pt.getHostname());
				onlineIPs.add(d);
			}
		}
		return onlineIPs;
	}

}
