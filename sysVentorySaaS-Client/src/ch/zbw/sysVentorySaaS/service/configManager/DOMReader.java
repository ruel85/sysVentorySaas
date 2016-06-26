package ch.zbw.sysVentorySaaS.service.configManager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * DOMReader ist f�r das Handling mit XML-Dateien zust�ndig
 * 
 * @author Damjan Djuranovic
 *
 */
public class DOMReader {

	/**
	 * Liest die XML-Elemente aus einem Dokument aus, und gibt eine HashMap
	 * zur�ck
	 * 
	 * @param doc
	 * @param xmlRootElement
	 * @param xmlElements
	 * @return Gibt eine HashMap zur�ck, die alle XML-Elemente beinhaltet
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public HashMap<String, String> getHashMap(Document doc, String xmlRootElement, List<String> xmlElements)
			throws ParserConfigurationException, SAXException, IOException {
		HashMap<String, String> result = new HashMap<>();
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName(xmlRootElement);
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				for (String s : xmlElements) {
					result.put(s, eElement.getElementsByTagName(s).item(0).getTextContent());
				}

			}
		}
		return result;
	}

	/**
	 * Liest die XML-Elemente aus einer XML-Datei aus, und gibt eine HashMap
	 * zur�ck
	 * 
	 * @param xmlPath
	 * @param xmlRootElement
	 * @param xmlElements
	 * @return Gibt eine HashMap zur�ck, die alle XML-Elemente beinhaltet
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public HashMap<String, String> getHashMap(String xmlPath, String xmlRootElement, List<String> xmlElements)
			throws ParserConfigurationException, SAXException, IOException {
		HashMap<String, String> result = new HashMap<>();
		File inputFile = new File(xmlPath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(inputFile);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName(xmlRootElement);
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				for (String s : xmlElements) {
					result.put(s, eElement.getElementsByTagName(s).item(0).getTextContent());
				}

			}
		}
		return result;
	}

	/**
	 * Pr�ft eine XML gegen ein Schema, ob das XML valide ist
	 * 
	 * @param xmlPath
	 * @param xsdPath
	 * @throws SAXException
	 * @throws IOException
	 */
	public void isValidateXSD(String xmlPath, String xsdPath) throws SAXException, IOException {
		File inputFile = new File(xmlPath);
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = factory.newSchema(new StreamSource(xsdPath));
		Validator validator = schema.newValidator();
		validator.validate(new StreamSource(inputFile));
	}

}
