package ch.zbw.sysVentorySaas.App.DataAccessObject;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import ch.zbw.sysVentorySaas.App.model.User;

// Loging Page-Infos
public class LoginDao {

	private ConfigurablePasswordEncryptor passwordEncryptor;
	
	public static User validate(String user, String password) {

		UserDAO userdao = new UserDAO();

		for (User oneUser : userdao.getAllUsers()) {
			if (oneUser != null)
				if (oneUser.getUsername().equals(user) && 
						getPWEncryptor().checkPassword(password, oneUser.getPassword())) {
					return oneUser;
				}
		}
		return null;
	}
	
	public static ConfigurablePasswordEncryptor getPWEncryptor()
	{
		ConfigurablePasswordEncryptor passwordEncryptor;
		passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm("SHA-1");
		passwordEncryptor.setPlainDigest(true);
		return passwordEncryptor;
		
	}
}