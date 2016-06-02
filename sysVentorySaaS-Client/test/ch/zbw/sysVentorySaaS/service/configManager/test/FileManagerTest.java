package ch.zbw.sysVentorySaaS.service.configManager.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import ch.zbw.sysVentorySaaS.service.configManager.FileManager;



public class FileManagerTest {
	private FileManager fm;
	private final String path = System.getenv("APPDATA").toString()+"\\SysVentory\\putHereConfigFile";
	
	@Before
	public void setUp() throws Exception {
		fm = new FileManager();
	}
	
	@Test
	public void createFolder() {
		
		fm.createDirectory(path, true);
		assertTrue(new File(path).isDirectory());
	}
	
	@Test
	public void deleteFolder() throws IOException {
		File f = new File(path + "\\test");
		f.mkdirs();
		f.createNewFile();
		fm.cleanDirectory(path);
		assertFalse(f.exists());
	}
	
}