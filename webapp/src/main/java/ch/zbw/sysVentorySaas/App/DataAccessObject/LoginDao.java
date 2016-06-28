package ch.zbw.sysVentorySaas.App.DataAccessObject;
import ch.zbw.sysVentorySaas.App.helpers.PasswordEncryptor;
import ch.zbw.sysVentorySaas.App.model.User;

// Loging Page-Infos
public class LoginDao {

	public static User validate(String username, String password) {
		for (User oneUser : UserDAO.getAllUsers()) {
			if (oneUser != null) {
				if (oneUser.getUsername().equals(username) 
						&& password.equals(oneUser.getPassword())
						//&& PasswordEncryptor.getPWEncryptor().checkPassword(password, oneUser.getPassword())
						) {
					return oneUser;
				}
			}
		}
		return null;
	}
}