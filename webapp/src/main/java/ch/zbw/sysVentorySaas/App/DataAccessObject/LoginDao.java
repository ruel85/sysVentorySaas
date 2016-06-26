package ch.zbw.sysVentorySaas.App.DataAccessObject;

import ch.zbw.sysVentorySaas.App.helpers.PasswordEncryptor;
import ch.zbw.sysVentorySaas.App.model.User;

// Loging Page-Infos
public class LoginDao {

	public static User validate(String user, String password) {
		for (User oneUser : UserDAO.getAllUsers()) {
			if (oneUser != null) {
				if (oneUser.getUsername().equals(user) && 
						oneUser.getPassword() == PasswordEncryptor.getPWEncryptor().encryptPassword(password)) {
					return oneUser;
				}
			}
		}
		return null;
	}
}