package ch.zbw.sysVentorySaas.App.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.annotations.Parameter;
import org.jboss.logging.Param;

import ch.zbw.sysVentorySaas.App.DataAccessObject.ScanSettingDAO;
import ch.zbw.sysVentorySaas.App.model.ScanSetting;

@Path("/scansettings")
public class ScanSettingService {

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<ScanSetting> getAllScanSettings() {
		
		List<ScanSetting> lst = new ArrayList<ScanSetting>();
		ScanSettingDAO sDAO = new ScanSettingDAO();
		lst = sDAO.getAllScanSettings();
		return lst;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public ScanSetting getScansettingById(@PathParam("id") int id){
		
		ScanSetting s = new ScanSetting();
		ScanSettingDAO sDAO = new ScanSettingDAO();
		System.out.println("Got Param" + id);
		s= sDAO.getScanSettingById(id);	//todo Ruel
		return s;
	}
	
}
