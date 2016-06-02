package ch.zbw.sysVentorySaaS.service.powershellExecuter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PowershellExecuter {
	private String succeedMessage;
	private String failedMessage;

	public PowershellExecuter() {

	}

	public String readFile(String path) throws FileNotFoundException, IOException {
		String command = null;
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			command = sb.toString();
		}
		return command;
	}

	public void execute(String command) throws IOException {
		succeedMessage = "";
		failedMessage = "";
		command = "powershell.exe " + command;
		Process powerShellProcess = Runtime.getRuntime().exec(command);
		powerShellProcess.getOutputStream().close();
		String line;
		BufferedReader stdout = new BufferedReader(new InputStreamReader(powerShellProcess.getInputStream()));
		while ((line = stdout.readLine()) != null) {
			succeedMessage = succeedMessage.concat(line);
		}
		stdout.close();
		BufferedReader stderr = new BufferedReader(new InputStreamReader(powerShellProcess.getErrorStream()));
		while ((line = stderr.readLine()) != null) {
			failedMessage = failedMessage.concat(line);
		}
		stderr.close();
	}

	public String getSucceedMessage() {
		return succeedMessage;
	}

	public String getFailedMessage() {
		return failedMessage;
	}

}