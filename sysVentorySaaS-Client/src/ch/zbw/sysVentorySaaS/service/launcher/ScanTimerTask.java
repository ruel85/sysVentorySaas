package ch.zbw.sysVentorySaaS.service.launcher;

import java.util.HashMap;
import java.util.TimerTask;

import ch.zbw.sysVentorySaaS.service.configManager.DOMReader;
import ch.zbw.sysVentorySaaS.service.httpClient.MyHttpClient;

class ScanTimerTask extends TimerTask {
	private Main main;
	private MyHttpClient httpc;
	private DOMReader domr = new DOMReader();

	public ScanTimerTask(Main main) {
		this.main = main;
	}

	@Override
	public void run() {
		main.getLogger().info("check if a job is waiting");
		try {
			httpc = new MyHttpClient(main, main.getServer() + "/" + main.getUserId());
			domr = new DOMReader();
			HashMap<String, String> jobrequest = domr.getHashMap(httpc.sendGET(), main.getXmlRootElementJob(),
					main.getXmlElementsJob());
			if (Boolean.parseBoolean(jobrequest.get("timeToScan"))) { // nur zu testzecken!
				main.getLogger().info("job was found, starting job [OK]\n");
				main.executePowershell();
				main.getLogger().info("send xml-report to server");
				String message = httpc.post(main.getReportXmlPath());
				main.getLogger().info(message);
				if (message.isEmpty()) {
					main.getLogger().info("xml-report successfully was send to server [OK]\n");
				}
			} else {
				main.getLogger().info("no job is waiting [OK]\n");
			}

		} catch (Exception e) {
			if (e.getMessage() != null)
				main.getLogger().warning(e.getMessage() + "\n");
		}
	}
}