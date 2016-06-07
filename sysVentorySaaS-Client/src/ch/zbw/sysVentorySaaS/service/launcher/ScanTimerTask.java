package ch.zbw.sysVentorySaaS.service.launcher;

import java.util.HashMap;
import java.util.TimerTask;

import ch.zbw.sysVentorySaaS.service.configManager.DOMReader;
import ch.zbw.sysVentorySaaS.service.httpClient.MyHttpClient;

class ScanTimerTask extends TimerTask {
	private Main main;

	public ScanTimerTask(Main main) {
		this.main = main;
	}

	public void run() {
		main.getLogger().info("check if a job is waiting");
		try {
			boolean startScan;
			
			MyHttpClient httpc = new MyHttpClient();
			DOMReader domr = new DOMReader();
			HashMap<String, String> jobrequest = domr.getHashMap(httpc.get(), main.getXmlRootElementJob(), main.getXmlElementsJob());
			
			startScan = Boolean.parseBoolean(jobrequest.get("JobAvailable"));
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