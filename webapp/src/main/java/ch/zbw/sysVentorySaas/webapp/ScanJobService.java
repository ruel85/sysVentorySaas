package ch.zbw.sysVentorySaas.webapp;

import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.Uri;

import com.mysql.fabric.Response;

@Path("scanjobs")
public class ScanJobService {

	
	@POST
	@Path("/{uID}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_HTML)
	public String createScanJobByUID(@PathParam("uID") String uID, String xmlResult)
	{
		System.out.println("Neuer POST-Request mit uID = " + uID);
		
		//new ScanSettingDAO().setTimeToScan(uID, true);
		
		return "<html>"
				+ "<body>"
				+ "<p>Hallo. ScanJob erhalten mit der UID" + uID + ".</p>"
				+"<p>ScanJob wird nun verarbeitet...</p>"
				+ "</body>"
				+ ""
				+ "</html>";
		
	}
}
