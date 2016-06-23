package ch.zbw.sysVentorySaas.webapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.impl.Log4JLogger;

import ch.zbw.sysVentorySaas.App.DataAccessObject.CompanyDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ScanSettingDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.UserDAO;
import ch.zbw.sysVentorySaas.App.model.Company;
import ch.zbw.sysVentorySaas.App.model.ScanSetting;
import ch.zbw.sysVentorySaas.App.model.User;

@Path("/scansettings")
public class ScanSettingService {
		
		@GET
		@Produces(MediaType.APPLICATION_XML)
		public List<ScanSetting> getAllScanSettings(){
			
			List<ScanSetting> newList;
			
			ScanSettingDAO scDAO = new ScanSettingDAO();
			scDAO = new ScanSettingDAO();
			newList = scDAO.getAllScanSettings();
			if(newList != null && newList.size() > 0)
				return newList;
			
			new ArrayList<ScanSetting>();
			return null;
		}
		
		/*@GET
		@Path("/{uID}")
		@Produces(MediaType.APPLICATION_XML)
		public ScanSetting getScanSettingByUID(@PathParam("uID") String uID)
		{
			ScanSetting sReturn = null;
			System.out.println("UID: " + uID);
			UserDAO userDAO = new UserDAO();
			User user = userDAO.getUserByUID(uID);
			
			if(user == null)
				return new ScanSetting("User konnte aufgrund UID (" + uID + ") nicht ermittelt werden!", "", "", 1, false);
			else if(user.getCompany() == null)
				return new ScanSetting("Company ist auf User nicht gesetzt!", "", "", 1, false);
			
			Company comp = new CompanyDAO().getCompanyById(user.getCompany().getIdCompany());
			//System.out.println("Company-ID: "  + comp.getIdCompany());
			//System.out.println("ScanSetting-ID: "  + new ScanSettingDAO().getScanSettingById(comp.getIdCompany()).getIdCompany());
			
			try {
				sReturn = new ScanSettingDAO().getScanSettingById(comp.getIdCompany());	
			} catch (Exception e) {
				Log4JLogger log = new Log4JLogger();
				log.error(e.getMessage());
			}
			return sReturn;
		}*/
		
		@GET
		@Path("/{uID}")
		@Produces(MediaType.APPLICATION_XML)
		public ScanSetting getScanSettingByUID(@PathParam("uID") String uID)
		{
			ScanSetting sReturn = new ScanSetting();
			System.out.println("getScanSettingByUID: " + uID);
			User user; 			
			Company comp;
			
			/*try {
				user = UserDAO.getUserByUID(uID);
				if(user != null && user.getCompany() != null && user.getCompany().getIdCompany() != 0)
				{
					comp = CompanyDAO.getCompanyById(user.getCompany().getIdCompany());
					
					if(comp != null && comp.getIdCompany() != 0)
						return ScanSettingDAO.getScanSettingById(comp.getIdCompany());
				}
				
			} catch (Exception e) {
				Log4JLogger log = new Log4JLogger();
				log.error(e.getMessage());				
			}
			*/
			
			
			Random r = new Random();
			int i = r.nextInt(100) + 1;
			
			sReturn.setIpStart("192.168.1.30");
			sReturn.setIpEnd("192.168.1.80");
			sReturn.setIdCompany(-1);
			sReturn.setIdCompany(1);
			sReturn.setTimeToScan((i <= 50?true:false));
			sReturn.setIntervallHours(60);	
			return sReturn;
		}
}