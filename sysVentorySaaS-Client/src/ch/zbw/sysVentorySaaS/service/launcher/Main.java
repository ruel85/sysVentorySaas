package ch.zbw.sysVentorySaaS.service.launcher;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
//	private final String dataPath = System.getenv("APPDATA").toString() + "\\SysVentory";
	private final String dataPath = "C:\\SysVentory";
	private final String configPath = dataPath + "\\PutHereConfigFile";
	private final String reportPath = dataPath + "\\ScanResults";
	private final String reportXmlPath = reportPath + "\\wmi.xml";
	private final String logPath = dataPath + "\\Logs";
	private final String configXmlPath = configPath + "\\config.xml";
	private final String configXsdPath = configPath + "\\config.xsd";
	private final String loggingPath = logPath + "\\log.txt";
	private final String xmlRootElementConfig = "SysVentoryConfig";
	private final String xmlRootElementJob = "ScanSetting";
	private final List<String> xmlElementsConfig = Arrays.asList("UserId", "Server");
	private final List<String> xmlElementsJob = Arrays.asList("uID", "timeToScan");
	private final int checkIntervall = 10000;

	private String userId;
	private String server;
	private DOMReader domReader;
	private FileManager fm;
	private PowershellExecuter pe;
	private Logger logger;
	private FileHandler fh;

	public Main() {
		initDirectory();
		readConfig();
		getSettingsFromServer();
	}

	public static void main(String argv[]) {
		new Main();
	}

	public static void stop() {

	}

	public void initDirectory() {

		fm = new FileManager();
		fm.createDirectory(dataPath, false);
		fm.createDirectory(configPath, false);
		fm.createDirectory(reportPath, false);
		fm.createDirectory(logPath, false);
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
		if (new File(dataPath).exists() && new File(configPath).exists() && new File(reportPath).exists()
				&& new File(logPath).exists())
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
			HashMap<String, String> xmlContent = domReader.getHashMap(configXmlPath, xmlRootElementConfig,
					xmlElementsConfig);
			userId = xmlContent.get("UserId");
			server = xmlContent.get("Server");
			if (!server.isEmpty() && !userId.isEmpty())
				logger.info("config was reading successfully [OK]\n");
			else
				logger.warning("config can not be reading successfully [ERROR]\n");
		} catch (SAXException | IOException | ParserConfigurationException e) {
			logger.warning(e.getMessage() + "\n");
		}
	}

	public void getSettingsFromServer() {
		ScanTimerTask myTask = new ScanTimerTask(this);
		Timer myTimer = new Timer();
		myTimer.schedule(myTask, 0, checkIntervall);
	}

	public void executePowershell() {
		String path = configPath + "\\scanjob.ps1";
		logger.info("executing powershell-job (" + path + ") ");
		boolean successfully = false;
		pe = new PowershellExecuter();
		try {
			String fileContent = pe.readFile(path);
			successfully = pe.execute_method2(fileContent);
			if (successfully) {
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

	public String getDataPath() {
		return dataPath;
	}

	public String getConfigPath() {
		return configPath;
	}

	public String getReportPath() {
		return reportPath;
	}

	public String getConfigXmlPath() {
		return configXmlPath;
	}

	public String getConfigXsdPath() {
		return configXsdPath;
	}

	public String getLoggingPath() {
		return loggingPath;
	}

	public String getXmlRootElementConfig() {
		return xmlRootElementConfig;
	}

	public List<String> getXmlElementsConfig() {
		return xmlElementsConfig;
	}

	public String getUserId() {
		return userId;
	}

	public String getServer() {
		return server;
	}

	public DOMReader getDomReader() {
		return domReader;
	}

	public FileManager getFm() {
		return fm;
	}

	public PowershellExecuter getPe() {
		return pe;
	}

	public FileHandler getFh() {
		return fh;
	}

	public String getXmlRootElementJob() {
		return xmlRootElementJob;
	}

	public List<String> getXmlElementsJob() {
		return xmlElementsJob;
	}

	public String getReportXmlPath() {
		return reportXmlPath;
	}

	public String getLogPath() {
		return logPath;
	}

	public int getCheckIntervall() {
		return checkIntervall;
	}
	
	

}
