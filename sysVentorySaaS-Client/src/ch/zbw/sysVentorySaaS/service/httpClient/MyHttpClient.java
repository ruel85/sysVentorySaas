package ch.zbw.sysVentorySaaS.service.httpClient;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;


@SuppressWarnings("deprecation")
public class MyHttpClient {
	private String requestURI;;

	public MyHttpClient(String requestURI) {
		this.requestURI = requestURI;
	}

	public void post_old() throws Exception {
		HttpClient client = new DefaultHttpClient();
		String postURI = "post";
		HttpPost post = new HttpPost(requestURI);
		String xml = "";
		HttpEntity entity = new ByteArrayEntity(xml.getBytes("UTF-8"));
		post.setEntity(entity);
		HttpResponse response = client.execute(post);
		String result = EntityUtils.toString(response.getEntity());
		System.out.println("HTTP RESULT: " + result);
	}

	public void post(String sourcePath) throws IOException, TransformerException {
		
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

		System.out.println(result.toString());
		
		os.flush();
		connection.getResponseCode();
		connection.disconnect();
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
