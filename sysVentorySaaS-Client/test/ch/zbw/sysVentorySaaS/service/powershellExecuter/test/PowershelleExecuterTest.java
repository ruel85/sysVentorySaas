package ch.zbw.sysVentorySaaS.service.powershellExecuter.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;

import org.junit.Test;

import ch.zbw.sysVentorySaaS.service.powershellExecuter.PowershellExecuter;

public class PowershelleExecuterTest {

	@Test
	public void test() throws IOException {
		PowershellExecuter pe = new PowershellExecuter();
		String fileContent = pe.readFile("Files//Test_HelloWorld.ps1");
		pe.execute_method1(fileContent);
		assertEquals("Hello World!", pe.getSucceedMessage());
		assertEquals("", pe.getFailedMessage());
		String fileContentFail = pe.readFile("Files//Test_HelloWorldFail.ps1");
		pe.execute_method1(fileContentFail);
		assertEquals("", pe.getSucceedMessage());
		assertFalse(pe.getFailedMessage().equals(""));
	}

}
