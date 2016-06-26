package ch.zbw.sysVentorySaas.App.helpers;

import java.util.TimerTask;

import ch.zbw.sysVentorySaas.App.DataAccessObject.ScanSettingDAO;
import ch.zbw.sysVentorySaas.App.model.ScanSetting;

public class TimerThread extends TimerTask {
	private ScanSetting scanSetting;
	private int intervall;
	private boolean intervallHasChanged;

	public TimerThread(ScanSetting scanSetting) throws Exception {
		this.scanSetting = ScanSettingDAO.getScanSettingById(scanSetting.getIdCompany());
		this.intervall = scanSetting.getIntervallMinutes();
		intervallHasChanged = false;
	}

	@Override
	public void run() {
		try {
			if (ScanSettingDAO.getScanSettingById(scanSetting.getIdCompany()).getIntervallMinutes() != intervall) {
				this.cancel();
				this.intervallHasChanged = true;
			} else {
				this.scanSetting.setTimeToScan(true);
				ScanSettingDAO.saveScanSetting(scanSetting);
				System.out.println("ScanJob auf True gesetzt in " + scanSetting.getNetworkName());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isIntervallHasChanged() {
		return intervallHasChanged;
	}

	public ScanSetting getScanSetting() {
		return scanSetting;
	}

}
