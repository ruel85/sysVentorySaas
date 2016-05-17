package ch.zbw.sysVentorySaaS.service.snmpscanner.test;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;
import ch.zbw.sysVentorySaaS.model.Device;
import ch.zbw.sysVentorySaaS.service.snmp.SNMPScanner;

public class SNMPScannerTest {

	@Test
	public void test() throws IOException {
		SNMPScanner ips = new SNMPScanner("192.168.1.1", "192.168.1.254", 500);
		ArrayList<Device> devices = ips.getSNMPDevices();
		for (Device d : devices) {
			System.out.println(d.toString());
		}
	}

}
