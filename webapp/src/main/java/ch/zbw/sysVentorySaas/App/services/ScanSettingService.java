package ch.zbw.sysVentorySaas.App.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.zbw.sysVentorySaas.App.model.ScanSetting;

@Path("/scansettings")
public class ScanSettingService {

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<ScanSetting> getAllScanSettings() {
		
		List<ScanSetting> lst = new ArrayList<ScanSetting>();
		
		ScanSetting scanS1 = new ScanSetting("HOLDEREGGER", "192.168.1.1", "192.168.1.35", 1, true);
		lst.add(scanS1);
		ScanSetting scanS2 = new ScanSetting("DJURANOVIC", "192.168.1.1", "192.168.1.35", 1, false);		
		lst.add(scanS2);
		return lst;
	}
	
	@GET
	@Path("/{idScanSetting}")
	@Produces(MediaType.APPLICATION_XML)
	public ScanSetting getScansettingByUId(){
		return new ScanSetting();
	}
	
}
