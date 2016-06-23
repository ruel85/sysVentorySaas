package ch.zbw.sysVentorySaaS.service.powershellExecuter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellNotAvailableException;

public class PowershellExecuter {
	private String succeedMessage;
	private String failedMessage;

	public PowershellExecuter() {

	}

	public void execute_method1(String command) throws IOException {
		succeedMessage = "";
		failedMessage = "";
		command = "powershell.exe " + command;
		System.out.println(command);
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

	public boolean execute_method2(String command) throws PowerShellNotAvailableException {
		succeedMessage = "";
		failedMessage = "";
		try {
			PowerShell powerShell = PowerShell.openSession();
			boolean successfully = !powerShell.executeCommand(command).isError();
			powerShell.close();
			return successfully;
		} catch (Exception e) {
			return false;
		}

	}

	public String getFailedMessage() {
		return failedMessage;
	}

	public String getSucceedMessage() {
		return succeedMessage;
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

}