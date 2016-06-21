package ch.zbw.sysVentorySaas.webapp.Beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ch.zbw.sysVentorySaas.App.DataAccessObject.CompanyDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ScanSettingDAO;
import ch.zbw.sysVentorySaas.App.model.Company;
import ch.zbw.sysVentorySaas.App.model.ScanSetting;
import ch.zbw.sysVentorySaas.App.model.User;
import ch.zbw.sysVentorySaas.webapp.Util.SessionUtils;

@ManagedBean
@SessionScoped
public class ScanSettingBean implements Serializable{
	
	private static final long serialVersionUID = 1253310005019082700L;

	private int idCompany;
	private String networkName;
	private String ipStart;
	private String ipEnd;
	private int intervallHours;
	private boolean timeToScan;	
	
	CompanyDAO cDAO;
	private Company comp;
	private ScanSetting setting;
	
//	public ScanSettingBean()
//	{
//		init();
//	}
	
	public String checkScanSetting()
	{
		return "ScanSetting";
	}
	
	public void init()
	{
		User u = (User)SessionUtils.getSession().getAttribute("User");
		
		cDAO = new CompanyDAO();
		this.comp = cDAO.getCompanyById(u.getCompany().getIdCompany());
		
		this.idCompany = comp.getIdCompany();
		this.networkName = comp.getName();
		setting = comp.getScanSetting();
		this.networkName = setting.getNetworkName();
		this.ipStart = setting.getIpStart();
		this.ipEnd = setting.getIpEnd();
		this.intervallHours = setting.getIntervallHours();
		this.timeToScan = setting.getTimeToScan();
	}

	public int getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}

	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public String getIpStart() {
		return ipStart;
	}

	public void setIpStart(String ipStart) {
		this.ipStart = ipStart;
	}

	public String getIpEnd() {
		return ipEnd;
	}

	public void setIpEnd(String ipEnd) {
		this.ipEnd = ipEnd;
	}

	public int getIntervallHours() {
		return intervallHours;
	}

	public void setIntervallHours(int intervallHours) {
		this.intervallHours = intervallHours;
	}

	public boolean isTimeToScan() {
		return timeToScan;
	}

	public void setTimeToScan(boolean timeToScan) {
		this.timeToScan = timeToScan;
	}
}
