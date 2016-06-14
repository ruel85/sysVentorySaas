package ch.zbw.sysVentorySaas.App.services;

import java.util.ArrayList;
import java.util.List;

import ch.zbw.sysVentorySaas.App.model.ScanSetting;

public class ScanSettingService {

	public List<ScanSetting> getAllScanSettings() {
		
		List<ScanSetting> lst = new ArrayList<ScanSetting>();
		ScanSetting scanS1 = new ScanSetting("HOLDEREGGER", "192.168.1.1", "192.168.1.35", "07:00", 1, true);
		lst.add(scanS1);
		ScanSetting scanS2 = new ScanSetting("DJURANOVIC", "192.168.1.1", "192.168.1.35", "08:00", 1, false);		
		lst.add(scanS2);
		return null;
	}
}
