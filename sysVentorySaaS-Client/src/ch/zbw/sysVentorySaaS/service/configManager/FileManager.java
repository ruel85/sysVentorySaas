package ch.zbw.sysVentorySaaS.service.configManager;

import java.io.File;

/**
 * FileManager ist f�r den Umgang mit dem Filesystem verantwortlich
 * 
 * @author Damjan Djuranovic
 *
 */
public class FileManager {

	/**
	 * L�scht den Inhalt eines angegebenen Verzeichnis
	 * 
	 * @param path
	 */
	public void cleanDirectory(String path) {
		File dir = new File(path);
		for (File file : dir.listFiles()) {
			file.delete();
		}
	}

	/**
	 * Erstellt ein Verzeichnis
	 * 
	 * @param path
	 * @param overwrite
	 *            wenn TRUE, dann wird der Inhalt gel�scht, sollte Inhalt
	 *            vorhanden sein
	 */
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
