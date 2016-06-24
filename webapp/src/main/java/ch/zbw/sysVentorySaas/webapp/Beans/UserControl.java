package ch.zbw.sysVentorySaas.webapp.Beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import com.sun.javafx.collections.MappingChange.Map;

import ch.zbw.sysVentorySaas.App.DataAccessObject.ScanJobDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.UserDAO;
import ch.zbw.sysVentorySaas.App.model.ScanJob;
import ch.zbw.sysVentorySaas.App.model.User;
import ch.zbw.sysVentorySaas.webapp.Util.SessionUtils;

@ManagedBean
@SessionScoped
public class UserControl implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;

	private String password;
	private String message;
	private String user;
	private int tmpIdUser;

	private List<User> users;

	public String getPwd() {
		return password;
	}

	public void setPwd(String pwd) {
		this.password = pwd;
	}

	public String getMsg() {
		return message;
	}

	public void setMsg(String msg) {
		this.message = msg;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<User> getUsers() {
		UserDAO userDao = new UserDAO();
		this.users = userDao.getAllUsers();
		return this.users;
	}

	// validate userControl
	public String checkUserRights() {
		return "UserControl";
	}

	// logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "Login";
	}

	public void deleteUser() {
		for(User user : this.users)
		{
			if(user.getIdUser() == this.tmpIdUser)
			{
				UserDAO userDao = new UserDAO();
				userDao.deleteUser(user);
			}
		}
	}

	// action listener event
	public void attrListener(ActionEvent event) {
		this.tmpIdUser = (int) event.getComponent().getAttributes().get("iduser");
	}
}
