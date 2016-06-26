package ch.zbw.sysVentorySaas.App.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

import ch.zbw.sysVentorySaas.App.model.ScanSetting;

public class TimerManager extends TimerTask {
	private static ArrayList<TimerThread> timerList;
	private static int factor;
	private static ArrayList<ScanSetting> scanSettings;

	/**
	 * @param scanSettings
	 * @param testing
	 *            Falls durch einen JUnit-Test ausgeführt, bitte TRUE übergeben,
	 *            dann wird der Intervall um den Faktor 1000 verkürzt
	 * @throws Exception
	 */
	public TimerManager(List<ScanSetting> scanSettingsList, boolean testing) throws Exception {
		timerList = new ArrayList<TimerThread>();
		scanSettings = new ArrayList<ScanSetting>(scanSettingsList);
		if (testing)
			factor = 1000;
		else
			factor = 1;

		for (ScanSetting scanSetting : scanSettings) {
			TimerThread tt = new TimerThread(scanSetting);
			Timer myTimer = new Timer();
			myTimer.schedule(tt, scanSetting.getIntervallMinutes() * (60000 / factor),
					scanSetting.getIntervallMinutes() * (60000 / factor));
			timerList.add(tt);
		}
	}

	/**
	 * @param scanSetting
	 *            Fügt dem TimerManager ein neues ScanSetting hinzu und startet
	 *            dieses automatisch mit dem Intervall
	 * @throws Exception
	 */
	public static void addScanSetting(ScanSetting scanSetting) throws Exception {
		TimerThread tt = new TimerThread(scanSetting);
		Timer myTimer = new Timer();
		myTimer.schedule(tt, scanSetting.getIntervallMinutes() * (60000 / factor),
				scanSetting.getIntervallMinutes() * (60000 / factor));
		timerList.add(tt);
		System.out.println("Neues ScanSetting ist hinzugekommen: " + scanSetting.getNetworkName());
	}

	/**
	 * @param scanSetting
	 *            Entfernt aus dem TimerManager ein ScanSetting und beendet
	 *            dessen Intervall
	 */
	public static void removeScanSetting(ScanSetting scanSetting) {
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
				TimerThread tt;
				try {
					tt = new TimerThread(scanSetting);
					Timer myTimer = new Timer();
					myTimer.schedule(tt, scanSetting.getIntervallMinutes() * (60000 / factor),
							scanSetting.getIntervallMinutes() * (60000 / factor));
					iterator.remove();
					iterator.add(tt);
					System.out.println("ScanSetting hat geändert von: " + th.getScanSetting().getNetworkName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	public static ArrayList<TimerThread> getTimerList() {
		return timerList;
	}

	public static void setTimerList(ArrayList<TimerThread> timerList) {
		TimerManager.timerList = timerList;
	}

	public static int getFactor() {
		return factor;
	}

	public static void setFactor(int factor) {
		TimerManager.factor = factor;
	}

	public static ArrayList<ScanSetting> getScanSettings() {
		return scanSettings;
	}

	public static void setScanSettings(ArrayList<ScanSetting> scanSettings) {
		TimerManager.scanSettings = scanSettings;
	}
	
	

}
