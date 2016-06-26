package ch.zbw.sysVentorySaas.webapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.xml.ws.Response;
import javax.xml.ws.ResponseWrapper;

import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.log4j.Logger;
import org.mockito.internal.stubbing.answers.ThrowsException;

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
		
		@GET
		@Path("/{uID}")
		@Produces(MediaType.APPLICATION_XML)
		public ScanSetting getScanSettingByUID(@PathParam("uID") String uID) throws ServiceNotFoundException
		{
			ScanSetting sReturn = new ScanSetting();
			System.out.println("getScanSettingByUID: " + uID);
			User user; 			
			Company comp;

			// Todo Ruel
//			try {
//				user = UserDAO.getUserByUID(uID);
//				if(user != null && user.getCompany() != null && user.getCompany().getIdCompany() != 0)
//				{
//					int idCompany = CompanyDAO.getCompanyById(user.getCompany().getIdCompany()).getIdCompany();
//					
//					if(idCompany !=0)
//					{
//						
//						sReturn = ScanSettingDAO.getScanSettingById(idCompany);
//						return sReturn;
//					}					
//				}
//				
//			} catch (Exception e) {
//				
//				logger.error(e.getMessage());
//			}
			
			Random r = new Random();
			int i = r.nextInt(100) + 1;
			sReturn.setTimeToScan((i <= 50?true:false));
			
			sReturn.setIpStart("192.168.1.30");
			sReturn.setIpEnd("192.168.1.80");
			sReturn.setIdCompany(-1);
			sReturn.setIntervallHours(60);
			
			return sReturn;
		}
}