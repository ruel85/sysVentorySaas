package ch.zbw.sysVentorySaaS.service.configManager;

import java.io.File;

public class FileManager {

	public FileManager() {

	}

	public void cleanDirectory(String path) {
		File dir = new File(path);
		for (File file : dir.listFiles()) {
			file.delete();
		}
	}

	public void createDirectory(String path, boolean overwrite) {
		File theDir = new File(path);
		if (!theDir.exists() || overwrite) {
			try {
				theDir.mkdir();
			} catch (SecurityException se) {
				se.printStackTrace();
			}
		}
	}

	@Override
	public String toString() {
		return "FileManager []";
	}

}
