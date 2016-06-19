package ch.zbw.sysVentorySaas.webapp.Util;

import java.sql.Connection;
import java.sql.DriverManager;


public class DataConnect {

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://ruelholderegger.ch:3306/SysVentorySaas?useSSL=false&serverTimezone=Europe/Paris", "SysVentoryAdmin", "vdjjmf#n$ri7cr!?+RX7ZVbY5");
			return con;
		} catch (Exception ex) {
			System.out.println("Database.getConnection() Error -->"
					+ ex.getMessage());
			return null;
		}
	}
	
	public static void close(Connection con) {
		try {
			con.close();
		} catch (Exception ex) {
		}
	}
}