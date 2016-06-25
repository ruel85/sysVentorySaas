package ch.zbw.sysVentorySaas.webapp.Beans;

import java.io.Serializable;
import java.util.List;

import ch.zbw.sysVentorySaas.App.DataAccessObject.CompanyDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.UserDAO;
import ch.zbw.sysVentorySaas.App.model.Company;
import ch.zbw.sysVentorySaas.App.model.User;

public class AdminBean implements Serializable {

	private static final long serialVersionUID = 7516066709203378407L;

	private long countCompany;
	private long countDevicesPerCompanyAvarge; //muss noch gemacht werden
	private long countUsers;
	
	// liefert die Daten für das Dashboard
	public void refreshDashboard() {
		countCompany = 0;
		countDevicesPerCompanyAvarge = 0;
		countUsers = 0;
		
		// zählt wieviele Firmen existieren
		for(Company c : CompanyDAO.getAllCompanies()) {
			countCompany++;
		}
		// zählt wieviele User existieren
		for(User u : UserDAO.getAllUsers()) {
			countUsers++;
		}
		
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
