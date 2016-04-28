package ch.zbw.sysVentorySaaS.service.ipscanner;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PingThread implements Runnable {
	private String ipv4;
	private int pingTime;
	private volatile boolean reachable;

	public PingThread(String ip, int pingTime) {
		this.ipv4 = ip;
		this.pingTime = pingTime;
	}

	@Override
	public void run() {
		try {
			InetAddress inet = InetAddress.getByName(ipv4);
			reachable = inet.isReachable(pingTime);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isReachable() {
		return reachable;
	}

	public String getIpv4() {
		return ipv4;
	}
}
