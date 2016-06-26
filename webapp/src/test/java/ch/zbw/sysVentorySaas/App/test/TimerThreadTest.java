package ch.zbw.sysVentorySaas.App.test;

import java.util.ArrayList;
import java.util.Timer;
import org.junit.Test;
import ch.zbw.sysVentorySaas.App.helpers.TimerManager;
import ch.zbw.sysVentorySaas.App.model.ScanSetting;

public class TimerThreadTest {

	@Test
	public void test() throws Exception {
		ScanSetting ss1 = new ScanSetting("ScanJob1", "192.168.1.1", "192.168.1.254", 1, false);
		ScanSetting ss2 = new ScanSetting("ScanJob2", "192.168.1.1", "192.168.1.254", 2, false);
		ScanSetting ss3 = new ScanSetting("ScanJob3", "192.168.1.1", "192.168.1.254", 3, false);
		ScanSetting ss4 = new ScanSetting("ScanJob4", "192.168.1.1", "192.168.1.254", 4, false);
		ScanSetting ss5 = new ScanSetting("ScanJob5", "192.168.1.1", "192.168.1.254", 5, false);
		ScanSetting ss6 = new ScanSetting("ScanJob6", "192.168.1.1", "192.168.1.254", 6, false);

		ArrayList<ScanSetting> scanSettings = new ArrayList<ScanSetting>();
		scanSettings.add(ss1);
		scanSettings.add(ss2);
		scanSettings.add(ss3);
		scanSettings.add(ss4);
		scanSettings.add(ss5);

		TimerManager tm = new TimerManager(scanSettings, true); // false wenn nicht im JUnit!
		Timer myTimer = new Timer();
		myTimer.schedule(tm, 0, 1000);
		
		sleep(4000);

		ss1.setIntervallHours(5);

		sleep(1000);

		tm.addScanSetting(ss6);

		sleep(4000);

		tm.removeScanSetting(ss2);

		sleep(20000);
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
