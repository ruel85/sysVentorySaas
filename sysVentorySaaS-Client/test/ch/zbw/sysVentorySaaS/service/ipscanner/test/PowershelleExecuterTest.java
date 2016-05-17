package ch.zbw.sysVentorySaaS.service.ipscanner.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import ch.zbw.sysVentorySaaS.powershellExecuter.PowershellExecuter;

public class PowershelleExecuterTest {

	@Test
	public void test() throws IOException {
		PowershellExecuter pe = new PowershellExecuter();
		String fileContent = pe.readFile("Files//Test_HelloWorld.ps1");
		pe.execute(fileContent);
		assertEquals("Hello World!", pe.getSucceedMessage());
		assertEquals("", pe.getFailedMessage());
		String fileContentFail = pe.readFile("Files//Test_HelloWorldFail.ps1");
		pe.execute(fileContentFail);
		assertEquals("", pe.getSucceedMessage());
		assertFalse(pe.getFailedMessage().equals(""));
	}

}
