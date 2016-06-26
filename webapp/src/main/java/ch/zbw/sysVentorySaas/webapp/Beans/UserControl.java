package ch.zbw.sysVentorySaas.webapp.Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.component.html.HtmlInputText;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import ch.zbw.sysVentorySaas.App.DataAccessObject.UserDAO;
import ch.zbw.sysVentorySaas.App.helpers.MD5Hash;
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

	public void loadList() {
		UserDAO userDao = new UserDAO();
		List<User> allUsers = userDao.getAllUsers();
		User loggedUser = SessionUtils.getUser();
		this.users = new ArrayList<User>();
		for(User user : allUsers)
		{
			if(user.getCompany().getIdCompany() == loggedUser.getCompany().getIdCompany())
			{
				this.users.add(user);
			}
		}
	}

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

		return this.users;
	}

	// validate userControl
	public String checkUserRights() {
		if(this.users == null)
		{
			this.loadList();
		}
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

	public void saveChanges() {
		for (User user : this.users) {
			if(user.getuID() == null)
			{
				user.setuID(MD5Hash.getMD5Hash(user.getUsername()).toString());
			}
			UserDAO.saveUser(user);
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

	public void setUser(User user) {
		this.user = user;
	}

	// fügt einen leeren Benutzer hinzu
	public String addUser() {
		User user = new User();
		user.setCompany(SessionUtils.getUser().getCompany());
		this.users.add(user);
		return null;
	}
}
