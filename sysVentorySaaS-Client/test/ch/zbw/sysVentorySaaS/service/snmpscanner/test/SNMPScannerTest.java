package ch.zbw.sysVentorySaaS.service.snmpscanner.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.snmp4j.smi.OID;

import ch.zbw.sysVentorySaaS.service.ipscanner.IPScanner;
import ch.zbw.sysVentorySaaS.service.snmp.SNMPScanner;

public class SNMPScannerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		IPScanner ips = new IPScanner("192.168.1.1", "192.168.1.254", 20000);
		for(String ip : ips.getReachableIPs()) {
			SNMPScanner snmps = new SNMPScanner("udp:"+ip+"/161");
			try {
				snmps.start();
						String name = snmps.getAsString(new OID(".1.3.6.1.2.1.1.5.0"));
						if(name != null) {
							System.out.println(name);
							
						}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
