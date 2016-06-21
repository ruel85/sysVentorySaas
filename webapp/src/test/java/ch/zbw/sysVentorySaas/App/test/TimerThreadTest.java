package ch.zbw.sysVentorySaas.App.test;

import java.util.ArrayList;
import java.util.Timer;
import org.junit.Test;
import ch.zbw.sysVentorySaas.App.helpers.TimerManager;
import ch.zbw.sysVentorySaas.App.model.ScanSetting;

public class TimerThreadTest {

	@Test
	public void test() {
		ScanSetting ss1 = new ScanSetting("Network1", "192.168.1.1", "192.168.1.254", 1, false);
		ScanSetting ss2 = new ScanSetting("Network2", "192.168.1.1", "192.168.1.254", 2, false);
		ScanSetting ss3 = new ScanSetting("Network3", "192.168.1.1", "192.168.1.254", 3, false);
		ScanSetting ss4 = new ScanSetting("Network4", "192.168.1.1", "192.168.1.254", 4, false);
		ScanSetting ss5 = new ScanSetting("Network5", "192.168.1.1", "192.168.1.254", 5, false);
		ScanSetting ss6 = new ScanSetting("Network6", "192.168.1.1", "192.168.1.254", 5, false);

		ArrayList<ScanSetting> scanSettings = new ArrayList<>();
		scanSettings.add(ss1);
		scanSettings.add(ss2);
		scanSettings.add(ss3);
		scanSettings.add(ss4);
		scanSettings.add(ss5);

		TimerManager tm = new TimerManager(scanSettings, true); // false wenn nicht im JUnit!
		Timer myTimer = new Timer();
		myTimer.schedule(tm, 0, 1000);
		
		sleep(2000);

		ss1.setIntervallHours(3);

		sleep(1000);

		tm.addScanSetting(ss6);

		sleep(4000);

		tm.removeScanSetting(ss2);

		sleep(10000);
	}

	public void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
