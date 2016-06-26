package ch.zbw.sysVentorySaas.App.helpers;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

public final class PasswordEncryptor {
	
	public static final ConfigurablePasswordEncryptor getPWEncryptor() {
		ConfigurablePasswordEncryptor passwordEncryptor;		
		passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm("SHA-1");
		passwordEncryptor.setPlainDigest(true);
		return passwordEncryptor;
	}

	public static boolean checkPassword(String plainPassword, String passwordUser) {
		return getPWEncryptor().checkPassword(plainPassword,
				passwordUser);
	}
}
