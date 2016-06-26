package ch.zbw.sysVentorySaaS.service.ipScanner;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;

/**
 * PingThread ist eine einzelne IsReachable-Abfrage
 * 
 * @author Damjan Djuranovic
 *
 */
public class PingThread implements Runnable {
	private String ipv4;
	private String hostname;
	private String macAddress;
	private int timeout;
	private volatile boolean reachable;

	/**
	 * @param ipv4
	 * @param timeout
	 */
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
			NetworkInterface network = NetworkInterface.getByInetAddress(inet);
			if (network != null) {
				byte[] mac = network.getHardwareAddress();
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < mac.length; i++) {
					sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? ":" : ""));
				}
				macAddress = sb.toString();
			} else {

			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 */
	public String getHostname() {
		return hostname;
	}

	/**
	 * @return
	 */
	public String getIpv4() {
		return ipv4;
	}

	/**
	 * @return
	 */
	public String getMacAddress() {
		return macAddress;
	}

	/**
	 * @return
	 */
	public boolean isReachable() {
		return reachable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hostname == null) ? 0 : hostname.hashCode());
		result = prime * result + ((ipv4 == null) ? 0 : ipv4.hashCode());
		result = prime * result + ((macAddress == null) ? 0 : macAddress.hashCode());
		result = prime * result + (reachable ? 1231 : 1237);
		result = prime * result + timeout;
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
		PingThread other = (PingThread) obj;
		if (hostname == null) {
			if (other.hostname != null)
				return false;
		} else if (!hostname.equals(other.hostname))
			return false;
		if (ipv4 == null) {
			if (other.ipv4 != null)
				return false;
		} else if (!ipv4.equals(other.ipv4))
			return false;
		if (macAddress == null) {
			if (other.macAddress != null)
				return false;
		} else if (!macAddress.equals(other.macAddress))
			return false;
		if (reachable != other.reachable)
			return false;
		if (timeout != other.timeout)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PingThread [ipv4=" + ipv4 + ", hostname=" + hostname + ", macAddress=" + macAddress + ", timeout="
				+ timeout + ", reachable=" + reachable + "]";
	}

}
