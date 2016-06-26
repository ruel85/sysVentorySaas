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

			try {
				user = UserDAO.getUserByUID(uID);
				if(user != null && user.getCompany() != null && user.getCompany().getIdCompany() != 0)
				{
					int idCompany = CompanyDAO.getCompanyById(user.getCompany().getIdCompany()).getIdCompany();
					
					if(idCompany !=0)
					{
						sReturn = ScanSettingDAO.getScanSettingById(idCompany);
						// Weil das zurückgeben des sReturn-Objektes nicht geht und
						// Es eine LayHibernateException gibt...
						ScanSetting neuScanSetting = new ScanSetting();
						neuScanSetting.setIdCompany(sReturn.getIdCompany());
						neuScanSetting.setIntervallMinutes(sReturn.getIntervallMinutes());
						neuScanSetting.setIpEnd(sReturn.getIpEnd());
						neuScanSetting.setIpStart(sReturn.getIpStart());
						neuScanSetting.setNetworkName(sReturn.getNetworkName());
						neuScanSetting.setTimeToScan(sReturn.getTimeToScan());
						return neuScanSetting;
					}
				}
				throw new Exception();
				
			} catch (Exception e) {
				
				sReturn.setNetworkName("getUserByUID(): Fehler beim Ermitteln des Users mit UID ( " + uID + " ) oder der Company oder der ScanSetting!");
			}
			throw new WebApplicationException("UID ungültig.", 418);
		}
}