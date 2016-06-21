package ch.zbw.sysVentorySaas.App.helpers;

import java.util.TimerTask;
import ch.zbw.sysVentorySaas.App.model.ScanSetting;

public class TimerThread extends TimerTask {
	private ScanSetting scanSetting;
	private int intervall;
	private boolean intervallHasChanged;

	public TimerThread(ScanSetting scanSetting) {
		this.scanSetting = scanSetting;
		this.intervall = scanSetting.getIntervallHours();
		intervallHasChanged = false;
	}

	@Override
	public void run() {
		this.scanSetting.setTimeToScan(true);
		if (this.scanSetting.getIntervallHours() != intervall) {
			this.cancel();
			this.intervallHasChanged = true;
		}
	
		System.out.println("Intervall-Job ausgeführt auf " + scanSetting.getNetworkName());
	}

	public boolean isIntervallHasChanged() {
		return intervallHasChanged;
	}

	public ScanSetting getScanSetting() {
		return scanSetting;
	}
	
	

}
