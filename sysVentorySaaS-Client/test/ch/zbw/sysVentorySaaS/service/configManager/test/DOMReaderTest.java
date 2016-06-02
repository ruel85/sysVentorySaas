package ch.zbw.sysVentorySaaS.service.configManager.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import ch.zbw.sysVentorySaaS.service.configManager.DOMReader;

public class DOMReaderTest {
	private DOMReader domReader;
	private final String xmlPath = "Files/config.xml";
	private final String xsdPath = "Files/config.xsd";

	@Before
	public void setUp() throws Exception {
		domReader = new DOMReader();
	}

	@Test
	public void validateXML() throws SAXException, IOException {
		domReader.isValidateXSD(xmlPath, xsdPath);
	}
	
	@Test
	public void testXMLcontent() throws ParserConfigurationException, SAXException, IOException {
		HashMap<String, String> xmlContent = domReader.getHashMap(xmlPath);
		assertEquals("2d1a0484f40daceeef42967c4ac00911", xmlContent.get("UserId"));
		assertEquals("rdse.northeurope.cloudapp.azure.com", xmlContent.get("Server"));
	}
}
