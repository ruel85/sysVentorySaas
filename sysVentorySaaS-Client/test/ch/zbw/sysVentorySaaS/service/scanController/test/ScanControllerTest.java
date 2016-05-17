package ch.zbw.sysVentorySaaS.service.scanController.test;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;
import ch.zbw.sysVentorySaaS.model.Device;
import ch.zbw.sysVentorySaaS.service.scanController.ScanController;

public class ScanControllerTest {

	@Test
	public void test() throws IOException {
		ScanController sc = new ScanController("172.16.4.1", "172.16.4.254", 10000, 500);
		ArrayList<Device> devices = sc.getAllNetworkDevices();
		for(Device d : devices) {
			System.out.println("IP=" + d.getIpAdress() + ", NAME=" + d.getName() + ", MAC=" + d.getMacAdress());
		}
	}
}
