package ch.zbw.sysVentorySaaS.service.httpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class MyHttpClient {
	private static String requestURI = "http://www.mocky.io/v2/5756c6e30f0000b81c2f0038";

	public MyHttpClient() {

	}

	public void post() throws Exception {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://www.mocky.io/v2/5756b8e00f0000b61a2f001e");
		String xml = "<?xml version=\"1.0\"?><contact-info><name>Jane Smith</name><company>AT&amp;T</company><phone>(212) 555-4567</phone></contact-info>";
		HttpEntity entity = new ByteArrayEntity(xml.getBytes("UTF-8"));
		post.setEntity(entity);
		HttpResponse response = client.execute(post);
		String result = EntityUtils.toString(response.getEntity());
		System.out.println("HTTP RESULT: " + result);
	}

	public Document get() throws Exception {

		URL url = new URL(requestURI);
		String query = "";
		String response = "";

		// make connection
		URLConnection urlc = url.openConnection();

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

	private Document loadXMLFromString(String xml) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		return builder.parse(is);
	}

}
