package ch.zbw.sysVentorySaaS.service.ipScanner;

import java.util.ArrayList;

import ch.zbw.sysVentorySaas.App.model.Device;
import net.ripe.commons.ip.Ipv4;
import net.ripe.commons.ip.Ipv4Range;

/**
 * IPScanner managed die PingThreads
 * 
 * @author Damjan Djuranovic
 *
 */
public class IPScanner {
	private String ipMin;
	private String ipMax;
	private int pingTime;

	/**
	 * @param ipMin
	 * @param ipMax
	 * @param pingTime
	 */
	public IPScanner(String ipMin, String ipMax, int pingTime) {
		this.ipMin = ipMin;
		this.ipMax = ipMax;
		this.pingTime = pingTime;
	}

	/**
	 * Sucht nach erreichbaren IP-Adressen
	 * 
	 * @return Gibt eine ArrayList von Devices zurück, die alle erreichbaren Geräte
	 *         beinhaltet
	 */
	public ArrayList<Device> getReachableIPs() {
		ArrayList<String> ipList = new ArrayList<>();
		for (Ipv4 ipv4 : Ipv4Range.parse(ipMin + "-" + ipMax)) {
			ipList.add(ipv4.toString());
		}
		ArrayList<Thread> threads = new ArrayList<>();
		ArrayList<PingThread> pingThreads = new ArrayList<>();
		ArrayList<Device> onlineIPs = new ArrayList<>();
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
				Device d = new Device();
				d.setMacAddress(pt.getMacAddress());
				d.setIpAddress(pt.getIpv4());
				d.setName(pt.getHostname());
				onlineIPs.add(d);
			}
		}
		return onlineIPs;
	}

	/**
	 * @return
	 */
	public String getIpMin() {
		return ipMin;
	}

	/**
	 * @param ipMin
	 */
	public void setIpMin(String ipMin) {
		this.ipMin = ipMin;
	}

	/**
	 * @return
	 */
	public String getIpMax() {
		return ipMax;
	}

	/**
	 * @param ipMax
	 */
	public void setIpMax(String ipMax) {
		this.ipMax = ipMax;
	}

	/**
	 * @return
	 */
	public int getPingTime() {
		return pingTime;
	}

	/**
	 * @param pingTime
	 */
	public void setPingTime(int pingTime) {
		this.pingTime = pingTime;
	}

	@Override
	public String toString() {
		return "IPScanner [ipMin=" + ipMin + ", ipMax=" + ipMax + ", pingTime=" + pingTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ipMax == null) ? 0 : ipMax.hashCode());
		result = prime * result + ((ipMin == null) ? 0 : ipMin.hashCode());
		result = prime * result + pingTime;
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
		IPScanner other = (IPScanner) obj;
		if (ipMax == null) {
			if (other.ipMax != null)
				return false;
		} else if (!ipMax.equals(other.ipMax))
			return false;
		if (ipMin == null) {
			if (other.ipMin != null)
				return false;
		} else if (!ipMin.equals(other.ipMin))
			return false;
		if (pingTime != other.pingTime)
			return false;
		return true;
	}

}
