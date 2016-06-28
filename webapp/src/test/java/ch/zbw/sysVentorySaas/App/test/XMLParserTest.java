package ch.zbw.sysVentorySaas.App.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.validation.executable.ValidateOnExecution;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;

import org.xml.sax.SAXException;

import ch.zbw.sysVentorySaas.App.DataAccessObject.DeviceDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ScanSettingDAO;
import ch.zbw.sysVentorySaas.App.computers.Computers;
import ch.zbw.sysVentorySaas.App.computers.Computers.Computer;
import ch.zbw.sysVentorySaas.App.computers.Computers.Computer.Prozessoren.Prozessor;
import ch.zbw.sysVentorySaas.App.helpers.XMLToDAOMapper;
import ch.zbw.sysVentorySaas.App.model.Device;
import ch.zbw.sysVentorySaas.App.model.NetworkInterface;
import ch.zbw.sysVentorySaas.App.model.OperatingSystem;
import ch.zbw.sysVentorySaas.App.model.Printer;
import ch.zbw.sysVentorySaas.App.model.ScanSetting;
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
	public void TestReadDataWithJaxBUnmarshaller1() throws Throwable	{
		
		File fileXML = new File("Schema/computers.xml");
		FileInputStream fi = new FileInputStream(fileXML);
		ScanSetting scanSetting = ScanSettingDAO.getAllScanSettings().get(0);
		XMLToDAOMapper.importData(fi, scanSetting);
	}
	
	@Test
	public void TestReadDataWithJaxBUnmarshaller2() throws Throwable	{
		
		File fileXML = new File("Schema/computers2.xml");
		FileInputStream fi = new FileInputStream(fileXML);
		ScanSetting scanSetting = ScanSettingDAO.getScanSettingById(51);
		XMLToDAOMapper.importData(fi, scanSetting);
	}
	
	@Test
	public void TestReadDataWithJaxBUnmarshaller3() throws Throwable	{
		
		File fileXML = new File("Schema/computers3.xml");
		FileInputStream fi = new FileInputStream(fileXML);
		ScanSetting scanSetting = ScanSettingDAO.getScanSettingById(51);
		XMLToDAOMapper.importData(fi, scanSetting);
	}
	
	@Test(expected=Throwable.class)
	public void TestReadDataWithJaxBUnmarshaller4_Error() throws Throwable	{
		
		File fileXML = new File("Schema/computers_error.xml");
		FileInputStream fi = new FileInputStream(fileXML);
		ScanSetting scanSetting = ScanSettingDAO.getScanSettingById(51);
		XMLToDAOMapper.importData(fi, scanSetting);
	}	
}