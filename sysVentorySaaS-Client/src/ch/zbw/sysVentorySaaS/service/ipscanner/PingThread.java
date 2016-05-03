package ch.zbw.sysVentorySaaS.service.ipscanner;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PingThread implements Runnable {
	private String ipv4;
	private String hostname;
	private int timeout;
	private volatile boolean reachable;

	public PingThread(String ipv4, int timeout) {
		this.ipv4 = ipv4;
		this.timeout = timeout;
	}

	@Override
	public void run() {
		try {
			InetAddress inet = InetAddress.getByName(ipv4);
			reachable = inet.isReachable(timeout);
			hostname = inet.getHostName();
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
	
	public String getHostname() {
		return hostname;
	}
}
