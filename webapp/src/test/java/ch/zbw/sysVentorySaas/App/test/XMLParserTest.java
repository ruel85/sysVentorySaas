package ch.zbw.sysVentorySaas.App.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;

import org.xml.sax.SAXException;

import ch.zbw.sysVentorySaas.App.DataAccessObject.DeviceDAO;
import ch.zbw.sysVentorySaas.App.computers.Computers;
import ch.zbw.sysVentorySaas.App.computers.Computers.Computer;
import ch.zbw.sysVentorySaas.App.computers.Computers.Computer.Prozessoren.Prozessor;
import ch.zbw.sysVentorySaas.App.helpers.XMLToDAOMapper;
import ch.zbw.sysVentorySaas.App.model.Device;
import ch.zbw.sysVentorySaas.App.model.NetworkInterface;
import ch.zbw.sysVentorySaas.App.model.OperatingSystem;
import ch.zbw.sysVentorySaas.App.model.Printer;
import ch.zbw.sysVentorySaas.App.model.Software;
import ch.zbw.sysVentorySaas.App.computers.ObjectFactory;
import ch.zbw.sysVentorySaas.App.computers.XMLParserDOM;


public class XMLParserTest {

public static XMLParserDOM xmlParserDOM;
	
	@Before
	public void readXMLIntoDOM() throws ParserConfigurationException, IOException, SAXException{
		xmlParserDOM = new XMLParserDOM("Schema/computers.xsd", "Schema/computers.xml");
	}
	
	
	public void TestValidateXML() throws ParserConfigurationException, IOException, SAXException
	{
		xmlParserDOM.validateXML();
	}
	
	@Test
	public void TestReadDataWithJaxBUnmarshaller() throws Throwable	{
		
		File fileXML = new File("Schema/computers.xml");
		FileInputStream fi = new FileInputStream(fileXML);
		XMLToDAOMapper.importData(fi);
	}
}
