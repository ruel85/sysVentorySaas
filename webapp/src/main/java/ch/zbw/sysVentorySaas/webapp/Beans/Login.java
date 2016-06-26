package ch.zbw.sysVentorySaas.webapp.Beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import ch.zbw.sysVentorySaas.App.DataAccessObject.LoginDao;
import ch.zbw.sysVentorySaas.App.model.User;
import ch.zbw.sysVentorySaas.webapp.Util.SessionUtils;

@ManagedBean
@SessionScoped
public class Login implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;
	
	private String password;
	private String user;

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
			AdminBean bean = new AdminBean();
			return bean.checkUserRights();
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Benutzername oder Passwort ist nicht korrekt",
							"Bitte geben Sie die richtigen Informationen ein."));
			return "Login";
		}
	}

	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "Login";
	}
}
