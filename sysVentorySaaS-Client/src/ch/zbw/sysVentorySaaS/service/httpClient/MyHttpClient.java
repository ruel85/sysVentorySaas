package ch.zbw.sysVentorySaaS.service.httpClient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import ch.zbw.sysVentorySaaS.service.launcher.Main;

/**
 * MyHttpClient ist f�r die HTTP-Kommunikation verantwortlich
 * 
 * @author Damjan Djuranovic
 *
 */
public class MyHttpClient {
	private Main main;

	/**
	 * @param main
	 */
	public MyHttpClient(Main main) {
		this.main = main;
	}

	/**
	 * Erzeugt ein Document aus einem XML-Pfad
	 * 
	 * @param xml
	 *            Pfad zur XML
	 * @return
	 * @throws Exception
	 */
	private Document loadXMLFromString(String xml) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		return builder.parse(is);
	}

	/**
	 * Sendet einen GET-Request
	 * 
	 * @return Document mit dem Response
	 * @throws Exception
	 */
	public Document sendGET() throws Exception {
		URL obj = new URL(main.getServerGET() + "/" + main.getUserId());
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/xml");
		int responseCode = con.getResponseCode();
		StringBuffer response = new StringBuffer();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			main.getLogger().info("Response: " + response.toString() + " [OK]");
			return loadXMLFromString(response.toString());
		} else {
			main.getLogger().warning("HTTP-Status: " + responseCode);
			System.out.println(con.getURL());
			return null;
		}
	}

	/**
	 * Sendet einen POST-Request mit Angabe eines XML-Pfades
	 * 
	 * @param sourcePath
	 *            Pfad zur XML Datei
	 * @return
	 * @throws IOException
	 * @throws TransformerException
	 */
	public String sendPOST(String sourcePath) throws IOException, TransformerException {
		URL url = new URL(main.getServerPOST() + "/" + main.getUserId());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setInstanceFollowRedirects(false);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/xml");
		OutputStream os = connection.getOutputStream();
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		FileReader fileReader = new FileReader(sourcePath);
		StreamSource source = new StreamSource(fileReader);
		StreamResult result = new StreamResult(os);
		transformer.transform(source, result);
		os.flush();
		connection.getResponseCode();
		connection.disconnect();
		main.getLogger().info("ResponseCode: " + connection.getResponseCode());
		return String.valueOf(connection.getResponseCode());
	}

	@Override
	public String toString() {
		return "MyHttpClient [main=" + main + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((main == null) ? 0 : main.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyHttpClient other = (MyHttpClient) obj;
		if (main == null) {
			if (other.main != null)
				return false;
		} else if (!main.equals(other.main))
			return false;
		return true;
	}

}
