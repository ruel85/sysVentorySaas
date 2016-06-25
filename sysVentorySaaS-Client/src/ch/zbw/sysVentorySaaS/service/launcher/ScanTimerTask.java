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
		main.getLogger().info("check if a job is waiting ...");
		try {
			httpc = new MyHttpClient(main, main.getServerGET() + "/" + main.getUserId());
			domr = new DOMReader();
			HashMap<String, String> jobrequest = domr.getHashMap(httpc.sendGET(), main.getXmlRootElementJob(),
					main.getXmlElementsJob());
			if (Boolean.parseBoolean(jobrequest.get("timeToScan"))) {

				main.getLogger().info("job was found, starting job [OK]\n");
				main.getLogger()
						.info("|-----------------------------------> START SCAN <-----------------------------------|");
				int trys = 0;
				while (main.executePowershell() == false && trys <= 3) {
					trys++;
				}
				if (trys <= 3) {
					main.getLogger().info("sending xml-report to server ...");
					String status = httpc.sendPOST(main.getReportXmlPath());
					if (status.equals("200")) {
						main.getLogger().info("ScanJob successfully send to server [OK]");
						main.getLogger().info(
								"|-----------------------------------> END SCAN [OK] <-----------------------------------|\n\n");
					} else {
						main.getLogger().warning("ScanJob could not be send to server [ERROR]\n");
						main.getLogger().info(
								"|-----------------------------------> END SCAN [ERROR] <-----------------------------------|\n\n");
					}
				} else {
					main.getLogger().info(
							"ScanJob could not be send to server, because Powershell not executed successfully [ERROR]\n");
					main.getLogger().info(
							"|-----------------------------------> END SCAN [ERROR] <-----------------------------------|\n\n");
				}
			} else {
				main.getLogger().info("no job is waiting [OK]\n");
			}

		} catch (Exception e) {
			if (e.getMessage() != null)
				main.getLogger().warning(e.getMessage() + " [ERROR]\n");
		}
	}

	@Override
	public String toString() {
		return "ScanTimerTask [main=" + main + ", httpc=" + httpc + ", domr=" + domr + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((domr == null) ? 0 : domr.hashCode());
		result = prime * result + ((httpc == null) ? 0 : httpc.hashCode());
		result = prime * result + ((main == null) ? 0 : main.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScanTimerTask other = (ScanTimerTask) obj;
		if (domr == null) {
			if (other.domr != null)
				return false;
		} else if (!domr.equals(other.domr))
			return false;
		if (httpc == null) {
			if (other.httpc != null)
				return false;
		} else if (!httpc.equals(other.httpc))
			return false;
		if (main == null) {
			if (other.main != null)
				return false;
		} else if (!main.equals(other.main))
			return false;
		return true;
	}

}