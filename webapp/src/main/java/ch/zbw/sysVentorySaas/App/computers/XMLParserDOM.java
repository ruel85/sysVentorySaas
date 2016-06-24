package ch.zbw.sysVentorySaas.App.computers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import ch.zbw.sysVentorySaas.App.computers.Computers.Computer.Prozessoren.Prozessor;
import ch.zbw.sysVentorySaas.App.model.Device;
import ch.zbw.sysVentorySaas.App.model.NetworkInterface;
import ch.zbw.sysVentorySaas.App.model.OperatingSystem;
import ch.zbw.sysVentorySaas.App.model.Printer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.w3c.dom.*;

public class XMLParserDOM {

	String xmlFileName = null;
	String xsdFilename = null;
	File xmlFile = null;
	StreamSource xsdSource = null;
	private NodeList nodeList;;
	
	public XMLParserDOM(String xsdFilename, String xmlFilename) throws ParserConfigurationException, IOException, SAXException
	{	
		this.xmlFileName = xmlFilename;
		this.xsdFilename = xsdFilename;
	}
	
	public void validateXML() throws ParserConfigurationException, IOException, SAXException
	{
		xmlFile = new File(xmlFileName);
		SchemaFactory sFactory = SchemaFactory.newInstance(XMLConstants.XML_NS_PREFIX);
		xsdSource = new StreamSource(xsdFilename);
		Schema schema = sFactory.newSchema(xsdSource);
		Validator validator = schema.newValidator();
		validator.validate(new StreamSource(xmlFile));
	}
	
	public void fillDataFromXMLFile(InputStream iStream) throws SAXException, IOException, ParserConfigurationException
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringComments(true);
		DocumentBuilder db;
		db = dbf.newDocumentBuilder();
		Document doc = db.parse(iStream);
		nodeList = doc.getElementsByTagName("computers");
		
		for(int i = 0; i < nodeList.getLength();i=i+1)
		{
			Node node = nodeList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE)
			{
				Element elem = (Element)node;
			}
		}
	}
}
