package ch.zbw.sysVentorySaaS.service.ipscanner;

import java.util.ArrayList;
import net.ripe.commons.ip.Ipv4;
import net.ripe.commons.ip.Ipv4Range;

public class IPScanner {
	private String IPmin;
	private String IPmax;
	private int pingTime;

	public IPScanner(String IPmin, String IPmax, int pingTime) {
		this.IPmin = IPmin;
		this.IPmax = IPmax;
		this.pingTime = pingTime;
	}

	public ArrayList<String> getReachableIPs() {
		ArrayList<String> ipList = new ArrayList<>();
		for (Ipv4 ipv4 : Ipv4Range.parse(IPmin + "-" + IPmax)) {
			ipList.add(ipv4.toString());
		}
		ArrayList<Thread> threads = new ArrayList<>();
		ArrayList<PingThread> pingThreads = new ArrayList<>();
		ArrayList<String> onlineIPs = new ArrayList<>();
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
				onlineIPs.add(pt.getIpv4());
			}
		}
		return onlineIPs;
	}

}
