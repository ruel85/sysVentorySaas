package ch.zbw.sysVentorySaaS.service.powershellExecuter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellNotAvailableException;

/**
 * PowershellExcecuter ist für die Ausführung einer Powershell verantwortlich
 * 
 * @author Damjan Djuranovic
 *
 */
public class PowershellExecuter {
	private String succeedMessage;
	private String failedMessage;

	/**
	 * Führt ein Powershell-Command aus
	 * 
	 * @param command
	 * @return erfolgreich oder nicht
	 * @throws PowerShellNotAvailableException
	 */
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

	/**
	 * Error-Meldung für method1
	 * 
	 * @return
	 */
	public String getFailedMessage() {
		return failedMessage;
	}

	/**
	 * Success-Meldung für method1
	 * 
	 * @return
	 */
	public String getSucceedMessage() {
		return succeedMessage;
	}

	/**
	 * Liest den Inalt einer Datei in einen String
	 * 
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((failedMessage == null) ? 0 : failedMessage.hashCode());
		result = prime * result + ((succeedMessage == null) ? 0 : succeedMessage.hashCode());
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
		PowershellExecuter other = (PowershellExecuter) obj;
		if (failedMessage == null) {
			if (other.failedMessage != null)
				return false;
		} else if (!failedMessage.equals(other.failedMessage))
			return false;
		if (succeedMessage == null) {
			if (other.succeedMessage != null)
				return false;
		} else if (!succeedMessage.equals(other.succeedMessage))
			return false;
		return true;
	}

	/**
	 * @param succeedMessage
	 */
	public void setSucceedMessage(String succeedMessage) {
		this.succeedMessage = succeedMessage;
	}

	/**
	 * @param failedMessage
	 */
	public void setFailedMessage(String failedMessage) {
		this.failedMessage = failedMessage;
	}

	@Override
	public String toString() {
		return "PowershellExecuter [succeedMessage=" + succeedMessage + ", failedMessage=" + failedMessage + "]";
	}

}