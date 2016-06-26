package ch.zbw.sysVentorySaas.webapp;

import java.io.File;
import java.io.FileInputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import ch.zbw.sysVentorySaas.App.DataAccessObject.CompanyDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ScanSettingDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.UserDAO;
import ch.zbw.sysVentorySaas.App.helpers.XMLToDAOMapper;
import ch.zbw.sysVentorySaas.App.model.Company;
import ch.zbw.sysVentorySaas.App.model.ScanSetting;
import ch.zbw.sysVentorySaas.App.model.User;


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
		ScanSetting scanSetting = new ScanSetting(); 
		
		FileInputStream fi = new FileInputStream(xmlFile);
		User user = UserDAO.getUserByUID(uID);
		Company comp = user.getCompany();
		
		if(comp != null && comp.getIdCompany() != 0)
		{
			scanSetting = ScanSettingDAO.getScanSettingById(CompanyDAO.getCompanyById(user.getCompany().getIdCompany()).getIdCompany());
		}
		
		if(scanSetting == null || scanSetting.getIdCompany() == 0)
			throw new WebApplicationException("ScanSetting konnte nicht ermittelt werden",418);
				
		XMLToDAOMapper.importData(fi, scanSetting);
		
		return "<html>"
				+ "<body>"
				+ "<p>Hallo " + user.getUsername() + ". ScanJob erhalten mit der UID" + uID + ".</p>"
				+"<p>ScanJob wurde erstellt und verarbeitet.</p>"
				+ "</body>"
				+ ""
				+ "</html>";
	}
}
