package ch.zbw.sysVentorySaas.App.DataAccessObject;

import ch.zbw.sysVentorySaas.App.model.User;

// Loging Page-Infos
public class LoginDao {

	// 
	public static User validate(String user, String password) {

		UserDAO userdao = new UserDAO();

		for (User oneUser : userdao.getAllUsers()) {
			if (oneUser != null)
				if (oneUser.getUsername().equals(user) && oneUser.getPassword().equals(password)) {
					return oneUser;
				}
		}
		return null;
	}
}