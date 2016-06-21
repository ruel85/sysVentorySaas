package ch.zbw.sysVentorySaas.webapp;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ScanSettingDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.UserDAO;
import ch.zbw.sysVentorySaas.App.model.ScanSetting;
import ch.zbw.sysVentorySaas.App.model.User;

@Path("/scansettings")
public class ScanSettingResource {
		
		@GET
		@Produces(MediaType.APPLICATION_XML)
		public List<ScanSetting> getAllScanSettings(){
			ScanSettingDAO scDAO = new ScanSettingDAO();
			scDAO = new ScanSettingDAO();
			return scDAO.getAllScanSettings();
		}
		
		@GET
		@Path("/{uID}")
		@Produces(MediaType.APPLICATION_XML)
		public ScanSetting getScanSettingByUID(@PathParam("uID") String uID)
		{
			System.out.println("UID: " + uID);
			UserDAO userDAO = new UserDAO();
			User user = userDAO.getUserByUID(uID);
			
			if(user != null)
				return new ScanSetting("User konnte aufgrund UID (" + uID + ") nicht ermittelt werden!", "", "", 1, false);
			else if(user.getCompany() == null)
				return new ScanSetting("Company ist auf User nicht gesetzt!", "", "", 1, false);
			return user.getCompany().getScanSetting();		
}
}