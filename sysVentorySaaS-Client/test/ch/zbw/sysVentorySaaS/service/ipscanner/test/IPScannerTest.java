package ch.zbw.sysVentorySaaS.service.ipscanner.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.zbw.sysVentorySaaS.service.ipscanner.IPScanner;

public class IPScannerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		IPScanner ips = new IPScanner("10.32.107.1", "10.32.107.254", 20000);
		for(String ip : ips.getReachableIPs()) {
			System.out.println(ip);
		}
	}

}
