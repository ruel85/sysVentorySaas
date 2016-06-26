package ch.zbw.sysVentorySaas.App.DataAccessObject;

import ch.zbw.sysVentorySaas.App.helpers.PasswordEncryptor;
import ch.zbw.sysVentorySaas.App.model.User;

// Loging Page-Infos
public class LoginDao {

	public static User validate(String username, String password) {
		for (User oneUser : UserDAO.getAllUsers()) {
			if (oneUser != null) {
				if (oneUser.getUsername().equals(username) 
						//&& oneUser.getPassword() == PasswordEncryptor.getPWEncryptor().encryptPassword(password)
						) {
					return oneUser;
				}
			}
		}
		return null;
	}
}