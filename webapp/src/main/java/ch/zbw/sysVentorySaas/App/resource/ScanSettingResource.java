package ch.zbw.sysVentorySaas.App.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ScanSettingDAO;
import ch.zbw.sysVentorySaas.App.model.ScanSetting;
import ch.zbw.sysVentorySaas.App.services.ScanSettingService;

@Path("/scansettings")
public class ScanSettingResource {

	ScanSettingDAO scanSettingDAO = new ScanSettingDAO();
	ScanSettingService scanSettingService = new ScanSettingService();
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public ScanSetting getScanSetting(){
		return scanSettingDAO.getScanSettingById(1);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<ScanSetting> getMessages(){
		return scanSettingService.getAllScanSettings();
	}

}
