package ch.zbw.sysVentorySaas.App.helpers;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

import ch.zbw.sysVentorySaas.App.model.ScanSetting;

public class TimerManager extends TimerTask {
	private ArrayList<TimerThread> timerList;
	private int factor;

	/**
	 * @param scanSettings
	 * @param testing
	 *            Falls durch einen JUnit-Test ausgeführt, bitte TRUE übergeben,
	 *            dann wird der Intervall um den Faktor 1000 verkürzt
	 */
	public TimerManager(ArrayList<ScanSetting> scanSettings, boolean testing) {
		timerList = new ArrayList<>();
		if (testing)
			factor = 1000;
		else
			factor = 1;

		for (ScanSetting scanSetting : scanSettings) {
			TimerThread tt = new TimerThread(scanSetting);
			Timer myTimer = new Timer();
			myTimer.schedule(tt, 0, scanSetting.getIntervallHours() * (3600000 / factor));
			timerList.add(tt);
		}
	}

	/**
	 * @param scanSetting
	 *            Fügt dem TimerManager ein neues ScanSetting hinzu und startet
	 *            dieses automatisch mit dem Intervall
	 */
	public void addScanSetting(ScanSetting scanSetting) {
		TimerThread tt = new TimerThread(scanSetting);
		Timer myTimer = new Timer();
		myTimer.schedule(tt, 0, scanSetting.getIntervallHours() * (3600000 / factor));
		timerList.add(tt);
		System.out.println("Neues ScanSetting ist hinzugekommen: " + scanSetting.getNetworkName());
	}

	/**
	 * @param scanSetting
	 *            Entfernt aus dem TimerManager ein ScanSetting und beendet
	 *            dessen Intervall
	 */
	public void removeScanSetting(ScanSetting scanSetting) {
		for (ListIterator<TimerThread> iterator = timerList.listIterator(); iterator.hasNext();) {
			TimerThread th = iterator.next();
			if (th.getScanSetting() == scanSetting) {
				String networkName = th.getScanSetting().getNetworkName();
				th.cancel();
				iterator.remove();
				System.out.println("ScanSetting: " + networkName + " wurde gelöscht");
			}
		}

	}

	@Override
	public void run() {
		for (ListIterator<TimerThread> iterator = timerList.listIterator(); iterator.hasNext();) {
			TimerThread th = iterator.next();
			if (th.isIntervallHasChanged()) {
				ScanSetting scanSetting = th.getScanSetting();
				TimerThread tt = new TimerThread(scanSetting);
				Timer myTimer = new Timer();
				myTimer.schedule(tt, 0, scanSetting.getIntervallHours() * (3600000 / factor));
				iterator.remove();
				iterator.add(tt);
				System.out.println("ScanSetting hat geändert von: " + th.getScanSetting().getNetworkName());
			}
		}

	}

}
