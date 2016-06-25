package ch.zbw.sysVentorySaas.App.helpers;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

public final class PasswordEncryptor {
	public static ConfigurablePasswordEncryptor getPWEncryptor() {
		ConfigurablePasswordEncryptor passwordEncryptor;
		passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm("SHA-1");
		passwordEncryptor.setPlainDigest(false);
		return passwordEncryptor;
	}

	public static boolean checkPassword(String password, String passwordUser) {
		return getPWEncryptor().checkPassword(password,
				passwordUser);
	}
}
