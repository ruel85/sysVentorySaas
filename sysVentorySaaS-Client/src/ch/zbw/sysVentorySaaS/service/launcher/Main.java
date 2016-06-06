package ch.zbw.sysVentorySaaS.service.launcher;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Timer;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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
	private final String loggingPath = dataPath + "\\last_log.txt";
	private String userId;
	private String server;

	private DOMReader domReader;

	private FileManager fm;
	private PowershellExecuter pe;
	
    Logger logger;
    FileHandler fh;

	public Main() {
		initDirectory();
		readConfig();
		getSettingsFromServer();
	}

	public static void main(String[] args) {
		new Main();
	}

	public void initDirectory() {
		
		fm = new FileManager();
		fm.createDirectory(dataPath, false);
		fm.createDirectory(configPath, false);
		fm.createDirectory(reportPath, false);
		logger = Logger.getLogger("Logger"); 
		try {
			fh = new FileHandler(loggingPath);
			logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
			logger.warning(e.getMessage() + "\n");
		} 
		logger.info("check if data directories exists");
		if (new File(dataPath).exists() && new File(configPath).exists() && new File(reportPath).exists())
			logger.info("directories exists [OK]\n");
		else
			logger.info("directories does not exists [ERROR]\n");
	}

	public void readConfig() {
		domReader = new DOMReader();
		try {
			logger.info("check if config.xml is valid (" + configXmlPath + ") ");
			domReader.isValidateXSD(configXmlPath, configXsdPath);
			logger.info("config.xml is valid [OK]\n");
			logger.info("reading config from file ");
			HashMap<String, String> xmlContent = domReader.getHashMap(configXmlPath);
			userId = xmlContent.get("UserId");
			server = xmlContent.get("Server");
			if (!server.isEmpty() && !userId.isEmpty())
				logger.info("config was reading successfully [OK]\n");
			else
				logger.warning("config cant be reading successfully [ERROR]\n");
		} catch (SAXException | IOException | ParserConfigurationException e) {
			logger.warning(e.getMessage() + "\n");
		}
	}

	public void getSettingsFromServer() {
		ScanTimerTask myTask = new ScanTimerTask(this);
		Timer myTimer = new Timer();
		myTimer.schedule(myTask, 0, 10000);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			logger.warning(e.getMessage() + "\n");
		}
	}

	public void executePowershell() {
		String path = configPath + "\\scanjob.ps1";
		logger.info("executing powershell-job (" + path + ") ");
		pe = new PowershellExecuter();
		try {
			String fileContent = pe.readFile(path);
			pe.execute(fileContent);
			if (!pe.getSucceedMessage().isEmpty() && pe.getFailedMessage().isEmpty()) {
				logger.info("powershell was successfully executed [OK]\n");
			} else {
				logger.warning("powershell was not executed successfully [ERROR]\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.warning(e.getMessage() + "\n");
		}
	}

	public Logger getLogger() {
		return logger;
	}
	
	

}
