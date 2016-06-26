package ch.zbw.sysVentorySaas.webapp.Beans;

import java.io.Serializable;
import java.util.List;

import ch.zbw.sysVentorySaas.App.DataAccessObject.CompanyDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ScanSettingDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.UserDAO;

public class AdminBean implements Serializable {

	private static final long serialVersionUID = 7516066709203378407L;

	private long countCompany;
	private long countDevicesPerCompanyAvarge; // muss noch gemacht werden
	private long countUsers;

	public String checkUserRights()
	{
		this.countCompany = CompanyDAO.getAllCompanies().size();
		this.countUsers = UserDAO.getAllUsers().size();
		this.countDevicesPerCompanyAvarge = 0;
		return "Admin";
	}
	
	// liefert die Daten für das Dashboard
	public void refreshDashboard() {
		countCompany = CompanyDAO.getAllCompanies().size();
		countUsers = UserDAO.getAllUsers().size();
	}

	public long getCountCompany() {
		return countCompany;
	}

	public void setCountCompany(long countCompany) {
		this.countCompany = countCompany;
	}

	public long getCountDevicesPerCompanyAvarge() {
		return countDevicesPerCompanyAvarge;
	}

	public void setCountDevicesPerCompanyAvarge(long countDevicesPerCompanyAvarge) {
		this.countDevicesPerCompanyAvarge = countDevicesPerCompanyAvarge;
	}

	public long getCountUsers() {
		return countUsers;
	}

	public void setCountUsers(long countUsers) {
		this.countUsers = countUsers;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
