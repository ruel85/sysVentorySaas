package ch.zbw.sysVentorySaas.webapp;



import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ScanSettingDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.UserDAO;
import ch.zbw.sysVentorySaas.App.model.ScanSetting;
import ch.zbw.sysVentorySaas.App.services.ScanSettingService;

public class ScanSettingResource {
	ScanSettingService scanSettingService = new ScanSettingService();

	
	/*@GET
	@Produces(MediaType.APPLICATION_XML)
	public ScanSetting getScanSetting(){
		return scanSettingDAO.getScanSettingById(1);
	}*/
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<ScanSetting> getScanSettings(){
		return new ScanSettingDAO().getAllScanSettings();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public ScanSetting getScanSettingByUID(@PathParam("id") int uID){
		
		ScanSetting sEmty = new ScanSetting();
		ScanSettingDAO dao = new ScanSettingDAO();
		
		sEmty = dao.getScanSettingById(uID);	
		return sEmty;
	}
	
}
