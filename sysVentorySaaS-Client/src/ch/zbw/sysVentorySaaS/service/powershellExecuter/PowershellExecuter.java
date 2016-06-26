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
	private String message;

	/**
	 * Führt ein Powershell-Command aus
	 * 
	 * @param command
	 * @return erfolgreich oder nicht
	 * @throws PowerShellNotAvailableException
	 */
	public String execute_method2(String command) throws PowerShellNotAvailableException {
		message = "";
		try {
			PowerShell powerShell = PowerShell.openSession();
			 message = powerShell.executeCommand(command).getCommandOutput();
			powerShell.close();
			return message;
		} catch (Exception e) {
			return message;
		}

	}

	public String getMessage() {
		return message;
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


}