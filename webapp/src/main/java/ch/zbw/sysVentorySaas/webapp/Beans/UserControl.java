package ch.zbw.sysVentorySaas.webapp.Beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.component.html.HtmlInputText;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import ch.zbw.sysVentorySaas.App.DataAccessObject.UserDAO;
import ch.zbw.sysVentorySaas.App.model.User;
import ch.zbw.sysVentorySaas.webapp.Util.SessionUtils;

@ManagedBean
@SessionScoped
public class UserControl implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;

	private String password;
	private String username;
	private String email;
	private int idUser;
	private UIInput tmpUsername;
	private UIInput tmpEmail;
	private UIInput tmpPassword;
	private User user;

	private List<User> users;

	public String getPwd() {
		return password;
	}

	public void setPwd(String pwd) {
		this.password = pwd;
	}

	public String getUser() {
		return this.username;
	}

	public void setUser(String user) {
		this.username = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public UIInput getTmpUsername() {
		return tmpUsername;
	}

	public void setTmpUsername(UIInput tmpUser) {
		this.tmpUsername = tmpUser;
	}

	// list of all users
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

	// delete a user
	public void deleteUser() {
		for (User user : this.users) {
			if (user.getIdUser() == this.idUser) {
				UserDAO userDao = new UserDAO();
				userDao.deleteUser(user);
			}
		}
	}

	// action listener event
	public void userListener(ActionEvent event) {
		this.idUser = (int) event.getComponent().getAttributes().get("iduser");
	}

	public void saveUser() {
		for (User user : this.users) {
			if (user.getIdUser() == this.idUser) {
				
//				this.tmpUser.setUsername(this.username);
//				this.tmpUser.setPassword(this.password);
//				this.tmpUser.setEmail(this.email);

				String test = (String)this.tmpEmail.getValue();
				test = this.tmpEmail.getSubmittedValue().toString();
				String username = this.tmpUsername.getSubmittedValue().toString();
				String email = test;
				UserDAO userDao = new UserDAO();
				userDao.saveUser(user);
			}
		}
	}

	public UIInput getTmpEmail() {
		return tmpEmail;
	}

	public void setTmpEmail(UIInput tmpEmail) {
		this.tmpEmail = tmpEmail;
	}

	public UIInput getTmpPassword() {
		return tmpPassword;
	}

	public void setTmpPassword(UIInput tmpPassword) {
		this.tmpPassword = tmpPassword;
	}
}
