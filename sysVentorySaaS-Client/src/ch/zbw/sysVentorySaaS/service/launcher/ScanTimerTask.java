package ch.zbw.sysVentorySaaS.service.launcher;

import java.util.TimerTask;

class ScanTimerTask extends TimerTask {
	private Main main;

	public ScanTimerTask(Main main) {
		this.main = main;
	}

	public void run() {
		main.getLogger().info("check if a job is waiting");
		try {
			boolean startScan;
			// toDo: via REST checken of job vorhanden
			startScan = true;
			if (startScan) {
				main.getLogger().info("job was found, starting job [OK]\n");
				main.executePowershell();
			} else {
				main.getLogger().info("no job is waiting [OK]\n");
			}

		} catch (Exception e) {
			main.getLogger().warning(e.getMessage() + "\n");
		}
	}
}