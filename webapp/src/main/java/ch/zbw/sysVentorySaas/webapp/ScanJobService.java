package ch.zbw.sysVentorySaas.webapp;

import java.io.File;
import java.io.FileInputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ch.zbw.sysVentorySaas.App.helpers.XMLToDAOMapper;


@Path("scanjobs")
public class ScanJobService {

	
	@POST
	@Path("/{uID}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_HTML)
	public String createScanJobByUID(@PathParam("uID") String uID, File xmlResult) throws Throwable
	{
		System.out.println("Neuer POST-Request mit uID = " + uID);		
		File xmlFile = xmlResult;
		FileInputStream fi = new FileInputStream(xmlFile);
		XMLToDAOMapper.importData(fi);
				
		return "<html>"
				+ "<body>"
				+ "<p>Hallo. ScanJob erhalten mit der UID" + uID + ".</p>"
				+"<p>ScanJob wird nun verarbeitet...</p>"
				+ "</body>"
				+ ""
				+ "</html>";
		
	}
}
