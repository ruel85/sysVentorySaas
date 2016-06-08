package ch.zbw.sysVentorySaaS.service.launcher;

import java.util.HashMap;
import java.util.TimerTask;

import ch.zbw.sysVentorySaaS.service.configManager.DOMReader;
import ch.zbw.sysVentorySaaS.service.httpClient.MyHttpClient;

class ScanTimerTask extends TimerTask {
	private Main main;
	private MyHttpClient httpc = new MyHttpClient();
	private DOMReader domr = new DOMReader();

	public ScanTimerTask(Main main) {
		this.main = main;
	}

	public void run() {
		main.getLogger().info("check if a job is waiting");
		try {
			httpc = new MyHttpClient();
			domr = new DOMReader();
			HashMap<String, String> jobrequest = domr.getHashMap(httpc.get(), main.getXmlRootElementJob(), main.getXmlElementsJob());
			if (Boolean.parseBoolean(jobrequest.get("JobAvailable")) && jobrequest.get("UserId").equals(main.getUserId())) {
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