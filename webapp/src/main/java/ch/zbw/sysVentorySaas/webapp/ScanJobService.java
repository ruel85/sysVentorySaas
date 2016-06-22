package ch.zbw.sysVentorySaas.webapp;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("scanjobs")
public class ScanJobService {

	
	@POST
	@Path("/{uID}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_HTML)
	public String createScanJobByUID(@PathParam("uID") String uID)
	{
		System.out.println("Neuer POST-Request mit uID = " + uID);
		
		//new ScanSettingDAO().setTimeToScan(uID, true);
		
		return "<html>"
				+ "<body>"
				+ "<p>ScanJob erhalten mit der ID" + uID
				+ "</body>"
				+ ""
				+ "</html>";
		
	}
}
