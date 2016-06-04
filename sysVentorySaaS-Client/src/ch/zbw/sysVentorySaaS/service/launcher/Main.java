package ch.zbw.sysVentorySaaS.service.launcher;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import ch.zbw.sysVentorySaaS.service.configManager.DOMReader;
import ch.zbw.sysVentorySaaS.service.configManager.FileManager;
import ch.zbw.sysVentorySaaS.service.powershellExecuter.PowershellExecuter;

public class Main {
	private final String dataPath = System.getenv("APPDATA").toString() + "\\SysVentory";
	private final String configPath = dataPath + "\\PutHereConfigFile";
	private final String reportPath = dataPath + "\\ScanResults";
	private final String configXmlPath = configPath + "\\config.xml";
	private final String configXsdPath = configPath + "\\config.xsd";
	private String userId;
	private String server;

	private DOMReader domReader;

	private FileManager fm;
	private PowershellExecuter pe;

	public Main() {
		initDirectory();
		readConfig();
		getSettingsFromServer();
		executePowershell();
	}

	public static void main(String[] args) {
		new Main();
	}

	public void initDirectory() {
		System.out.print("check if data directories exists ... ");
		fm = new FileManager();
		fm.createDirectory(dataPath, false);
		fm.createDirectory(configPath, false);
		fm.createDirectory(reportPath, false);
		if (new File(dataPath).exists() && new File(configPath).exists() && new File(reportPath).exists())
			System.out.println("[OK]");
		else
			System.out.println("[ERROR]");
	}

	public void readConfig() {
		domReader = new DOMReader();
		try {
			System.out.print("check if config.xml is valid ... ");
			domReader.isValidateXSD(configXmlPath, configXsdPath);
			System.out.println("[OK]");
			System.out.print("reading config from file ... ");
			HashMap<String, String> xmlContent = domReader.getHashMap(configXmlPath);
			userId = xmlContent.get("UserId");
			server = xmlContent.get("Server");
			if (!server.isEmpty() && !userId.isEmpty())
				System.out.println("[OK]");
			else
				System.out.println("[ERROR]");
		} catch (SAXException | IOException | ParserConfigurationException e) {
			System.out.println("[ERROR]");
			e.printStackTrace();
		}
	}

	public void getSettingsFromServer() {
		
	}

	public void executePowershell() {
		System.out.print("executing powershell-job ... ");
		pe = new PowershellExecuter();
		String path = configPath + "\\scanjob.ps1";
		try {
			String fileContent = pe.readFile(path);
			pe.execute(fileContent);
			if (!pe.getSucceedMessage().isEmpty() && pe.getFailedMessage().isEmpty()) {
				System.out.println("[OK]");
			} else {
				System.out.println("[ERROR]");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
