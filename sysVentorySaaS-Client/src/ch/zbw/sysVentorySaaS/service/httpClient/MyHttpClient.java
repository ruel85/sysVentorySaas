package ch.zbw.sysVentorySaaS.service.httpClient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

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

public class MyHttpClient {
	private String requestURI;
	private Main main;

	public MyHttpClient(Main main, String requestURI) {
		this.requestURI = requestURI;
		this.main = main;
	}

	public String post(String sourcePath) throws IOException, TransformerException {

		URL url = new URL(requestURI);
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

		return result.toString();
	}

	public Document get() throws Exception {

		URL url = new URL(requestURI);
		String query = "";
		String response = "";

		// make connection
		URLConnection urlc = url.openConnection();
		urlc.setRequestProperty("Accept-Charset", "blaba");

		// use post mode
		urlc.setDoOutput(true);
		urlc.setAllowUserInteraction(false);

		// send query
		PrintStream ps = new PrintStream(urlc.getOutputStream());
		ps.print(query);
		ps.close();

		// get result
		BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
		String l = null;
		while ((l = br.readLine()) != null) {
			response += l;
		}
		br.close();
		return loadXMLFromString(response);
	}

	public Document sendGET() throws Exception {
		URL obj = new URL(requestURI);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("accept", "application/xml");
		int responseCode = con.getResponseCode();
		StringBuffer response = new StringBuffer();
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;

			System.out.println(in.readLine());
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
				System.out.println(inputLine);
			}
			in.close();
			return loadXMLFromString(response.toString());
		} else {
			main.getLogger().warning("HTTP-Status: " + responseCode);
			return null;
		}
	}

	private Document loadXMLFromString(String xml) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		return builder.parse(is);
	}

}
