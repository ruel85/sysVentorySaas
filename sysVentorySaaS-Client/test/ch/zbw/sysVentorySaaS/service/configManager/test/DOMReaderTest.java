package ch.zbw.sysVentorySaaS.service.configManager.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import ch.zbw.sysVentorySaaS.service.configManager.DOMReader;

public class DOMReaderTest {
	private DOMReader domReader;
	private final String xmlPath = "Files/config.xml";
	private final String xsdPath = "Files/config.xsd";
	private final String xmlRootElementConfig = "SysVentoryConfig";
	private final List<String> xmlElementsConfig = Arrays.asList("UserId", "ServerGET", "ServerPOST");

	@Before
	public void setUp() throws Exception {
		domReader = new DOMReader();
	}

	@Test
	public void testXMLcontent() throws ParserConfigurationException, SAXException, IOException {
		HashMap<String, String> xmlContent = domReader.getHashMap(xmlPath, xmlRootElementConfig, xmlElementsConfig);
		assertEquals("2d1a0484f40daceeef42967c4ac00911", xmlContent.get("UserId"));
		assertEquals("http://sysventorysaas.azurewebsites.net/webapi/scansettings", xmlContent.get("ServerGET"));
		assertEquals("http://sysventorysaas.azurewebsites.net/webapi/scanjobs", xmlContent.get("ServerPOST"));
	}

	@Test
	public void validateXML() throws SAXException, IOException {
		domReader.isValidateXSD(xmlPath, xsdPath);
	}
}
