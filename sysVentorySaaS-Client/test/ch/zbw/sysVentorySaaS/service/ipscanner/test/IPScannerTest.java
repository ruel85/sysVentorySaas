package ch.zbw.sysVentorySaaS.service.ipscanner.test;

import java.util.ArrayList;

import org.junit.Test;

import ch.zbw.sysVentorySaaS.model.Device;
import ch.zbw.sysVentorySaaS.service.ipscanner.IPScanner;

public class IPScannerTest {


	@Test
	public void test() {
		IPScanner ips = new IPScanner("192.168.1.1", "192.168.1.254", 10000);
		ArrayList<Device> ipList = ips.getReachableIPs();
		for(Device device : ipList) {
			System.out.println("IP=" + device.getIpAdress() + ", NAME=" + device.getName());
		}
	}

}