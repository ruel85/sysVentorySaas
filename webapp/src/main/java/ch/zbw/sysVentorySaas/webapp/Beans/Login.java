package ch.zbw.sysVentorySaas.webapp.Beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import ch.zbw.sysVentorySaas.App.DataAccessObject.CompanyDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.LoginDao;
import ch.zbw.sysVentorySaas.App.DataAccessObject.UserDAO;
import ch.zbw.sysVentorySaas.App.model.User;
import ch.zbw.sysVentorySaas.webapp.Util.SessionUtils;

@ManagedBean
@SessionScoped
public class Login implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;
	
	private String password;
	private String user;
	
	private long countCompany;
	private long countDevicesPerCompanyAvarge; // muss noch gemacht werden
	private long countUsers;

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String pwd) {
		this.password = pwd;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	//validate login
	public String validateUsernamePassword() {
		User user = LoginDao.validate(this.user, this.password);
		if (user != null) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("user", user);
			this.setCountCompany(CompanyDAO.getAllCompanies().size());
			this.setCountUsers(UserDAO.getAllUsers().size());
			this.setCountDevicesPerCompanyAvarge(0);
			return "Admin";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Benutzername oder Passwort ist nicht korrekt",
							"Bitte geben Sie die richtigen Informationen ein."));
			FacesContext.getCurrentInstance().renderResponse();
			return "Login";
		}
	}

	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "Login";
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
}
