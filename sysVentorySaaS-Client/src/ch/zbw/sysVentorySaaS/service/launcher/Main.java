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
	private final String dataPath = "C:\\SysVentory";
	private final String configPath = dataPath + "\\PutHereConfigFile";
	private final String reportPath = dataPath + "\\ScanResults";
	private final String reportXmlPath = reportPath + "\\wmi.xml";
	private final String logPath = dataPath + "\\Logs";
	private final String configXmlPath = configPath + "\\config.xml";
	private final String configXsdPath = configPath + "\\config.xsd";
	private final String loggingPath = logPath + "\\log.txt";
	private final String xmlRootElementConfig = "SysVentoryConfig";
	private final String xmlRootElementJob = "scanSetting";
	private final List<String> xmlElementsConfig = Arrays.asList("UserId", "ServerGET", "ServerPOST");
	private final List<String> xmlElementsJob = Arrays.asList("timeToScan");
	private final int checkIntervall = 10000;
	private String userId;
	private String serverGET;
	private String serverPOST;
	private DOMReader domReader;
	private FileManager fm;
	private PowershellExecuter pe;
	private static Logger logger;
	private FileHandler fh;

	public static void main(String argv[]) {
		new Main();
	}

	public static void stop() {
		logger.info("stopping service [OK]");
	}

	public Main() {
		initDirectory();
		readConfig();
		getSettingsFromServer();
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
		logger.info("check if data directories exists ...");
		if (new File(dataPath).exists() && new File(configPath).exists() && new File(reportPath).exists()
				&& new File(logPath).exists())
			logger.info("directories exists [OK]\n");
		else
			logger.info("directories does not exists [ERROR]\n");
	}

	public void readConfig() {
		domReader = new DOMReader();
		try {
			logger.info("check if config.xml is valid (" + configXmlPath + ") ...");
			domReader.isValidateXSD(configXmlPath, configXsdPath);
			logger.info("config.xml is valid [OK]\n");
			logger.info("reading config from file ");
			HashMap<String, String> xmlContent = domReader.getHashMap(configXmlPath, xmlRootElementConfig,
					xmlElementsConfig);
			userId = xmlContent.get("UserId");
			serverGET = xmlContent.get("ServerGET");
			serverPOST = xmlContent.get("ServerPOST");
			if (!serverGET.isEmpty() && !serverPOST.isEmpty() && !userId.isEmpty())
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

	public String getServerGET() {
		return serverGET;
	}

	public void setServerGET(String serverGET) {
		this.serverGET = serverGET;
	}

	public String getServerPOST() {
		return serverPOST;
	}

	public void setServerPOST(String serverPOST) {
		this.serverPOST = serverPOST;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setDomReader(DOMReader domReader) {
		this.domReader = domReader;
	}

	public void setFm(FileManager fm) {
		this.fm = fm;
	}

	public void setPe(PowershellExecuter pe) {
		this.pe = pe;
	}

	public void setFh(FileHandler fh) {
		this.fh = fh;
	}

	public int getCheckIntervall() {
		return checkIntervall;
	}

	public String getConfigPath() {
		return configPath;
	}

	public String getConfigXmlPath() {
		return configXmlPath;
	}

	public String getConfigXsdPath() {
		return configXsdPath;
	}

	public String getDataPath() {
		return dataPath;
	}

	public DOMReader getDomReader() {
		return domReader;
	}

	public FileHandler getFh() {
		return fh;
	}

	public FileManager getFm() {
		return fm;
	}

	public Logger getLogger() {
		return logger;
	}

	public String getLoggingPath() {
		return loggingPath;
	}

	public String getLogPath() {
		return logPath;
	}

	public PowershellExecuter getPe() {
		return pe;
	}

	public String getReportPath() {
		return reportPath;
	}

	public String getReportXmlPath() {
		return reportXmlPath;
	}

	public String getServer() {
		return serverGET;
	}

	public String getUserId() {
		return userId;
	}

	public List<String> getXmlElementsConfig() {
		return xmlElementsConfig;
	}

	public List<String> getXmlElementsJob() {
		return xmlElementsJob;
	}

	public String getXmlRootElementConfig() {
		return xmlRootElementConfig;
	}

	public String getXmlRootElementJob() {
		return xmlRootElementJob;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + checkIntervall;
		result = prime * result + ((configPath == null) ? 0 : configPath.hashCode());
		result = prime * result + ((configXmlPath == null) ? 0 : configXmlPath.hashCode());
		result = prime * result + ((configXsdPath == null) ? 0 : configXsdPath.hashCode());
		result = prime * result + ((dataPath == null) ? 0 : dataPath.hashCode());
		result = prime * result + ((domReader == null) ? 0 : domReader.hashCode());
		result = prime * result + ((fh == null) ? 0 : fh.hashCode());
		result = prime * result + ((fm == null) ? 0 : fm.hashCode());
		result = prime * result + ((logPath == null) ? 0 : logPath.hashCode());
		result = prime * result + ((loggingPath == null) ? 0 : loggingPath.hashCode());
		result = prime * result + ((pe == null) ? 0 : pe.hashCode());
		result = prime * result + ((reportPath == null) ? 0 : reportPath.hashCode());
		result = prime * result + ((reportXmlPath == null) ? 0 : reportXmlPath.hashCode());
		result = prime * result + ((serverGET == null) ? 0 : serverGET.hashCode());
		result = prime * result + ((serverPOST == null) ? 0 : serverPOST.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((xmlElementsConfig == null) ? 0 : xmlElementsConfig.hashCode());
		result = prime * result + ((xmlElementsJob == null) ? 0 : xmlElementsJob.hashCode());
		result = prime * result + ((xmlRootElementConfig == null) ? 0 : xmlRootElementConfig.hashCode());
		result = prime * result + ((xmlRootElementJob == null) ? 0 : xmlRootElementJob.hashCode());
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
		Main other = (Main) obj;
		if (checkIntervall != other.checkIntervall)
			return false;
		if (configPath == null) {
			if (other.configPath != null)
				return false;
		} else if (!configPath.equals(other.configPath))
			return false;
		if (configXmlPath == null) {
			if (other.configXmlPath != null)
				return false;
		} else if (!configXmlPath.equals(other.configXmlPath))
			return false;
		if (configXsdPath == null) {
			if (other.configXsdPath != null)
				return false;
		} else if (!configXsdPath.equals(other.configXsdPath))
			return false;
		if (dataPath == null) {
			if (other.dataPath != null)
				return false;
		} else if (!dataPath.equals(other.dataPath))
			return false;
		if (domReader == null) {
			if (other.domReader != null)
				return false;
		} else if (!domReader.equals(other.domReader))
			return false;
		if (fh == null) {
			if (other.fh != null)
				return false;
		} else if (!fh.equals(other.fh))
			return false;
		if (fm == null) {
			if (other.fm != null)
				return false;
		} else if (!fm.equals(other.fm))
			return false;
		if (logPath == null) {
			if (other.logPath != null)
				return false;
		} else if (!logPath.equals(other.logPath))
			return false;
		if (loggingPath == null) {
			if (other.loggingPath != null)
				return false;
		} else if (!loggingPath.equals(other.loggingPath))
			return false;
		if (pe == null) {
			if (other.pe != null)
				return false;
		} else if (!pe.equals(other.pe))
			return false;
		if (reportPath == null) {
			if (other.reportPath != null)
				return false;
		} else if (!reportPath.equals(other.reportPath))
			return false;
		if (reportXmlPath == null) {
			if (other.reportXmlPath != null)
				return false;
		} else if (!reportXmlPath.equals(other.reportXmlPath))
			return false;
		if (serverGET == null) {
			if (other.serverGET != null)
				return false;
		} else if (!serverGET.equals(other.serverGET))
			return false;
		if (serverPOST == null) {
			if (other.serverPOST != null)
				return false;
		} else if (!serverPOST.equals(other.serverPOST))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (xmlElementsConfig == null) {
			if (other.xmlElementsConfig != null)
				return false;
		} else if (!xmlElementsConfig.equals(other.xmlElementsConfig))
			return false;
		if (xmlElementsJob == null) {
			if (other.xmlElementsJob != null)
				return false;
		} else if (!xmlElementsJob.equals(other.xmlElementsJob))
			return false;
		if (xmlRootElementConfig == null) {
			if (other.xmlRootElementConfig != null)
				return false;
		} else if (!xmlRootElementConfig.equals(other.xmlRootElementConfig))
			return false;
		if (xmlRootElementJob == null) {
			if (other.xmlRootElementJob != null)
				return false;
		} else if (!xmlRootElementJob.equals(other.xmlRootElementJob))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Main [dataPath=" + dataPath + ", configPath=" + configPath + ", reportPath=" + reportPath
				+ ", reportXmlPath=" + reportXmlPath + ", logPath=" + logPath + ", configXmlPath=" + configXmlPath
				+ ", configXsdPath=" + configXsdPath + ", loggingPath=" + loggingPath + ", xmlRootElementConfig="
				+ xmlRootElementConfig + ", xmlRootElementJob=" + xmlRootElementJob + ", xmlElementsConfig="
				+ xmlElementsConfig + ", xmlElementsJob=" + xmlElementsJob + ", checkIntervall=" + checkIntervall
				+ ", userId=" + userId + ", serverGET=" + serverGET + ", serverPOST=" + serverPOST + ", domReader="
				+ domReader + ", fm=" + fm + ", pe=" + pe + ", fh=" + fh + "]";
	}

}
