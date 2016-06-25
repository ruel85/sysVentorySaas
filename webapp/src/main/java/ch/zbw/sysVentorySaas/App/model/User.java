package ch.zbw.sysVentorySaas.App.model;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.annotations.Entity;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import ch.zbw.sysVentorySaas.App.DataAccessObject.GroupDAO;
import ch.zbw.sysVentorySaas.App.helpers.GroupType;
import ch.zbw.sysVentorySaas.App.helpers.MD5Hash;

public class User {
	private int idUser;
	private String uID;
	private String username;
	private String password;
	private String email;
	
	private GroupType groupType;
	private Company company;
	
	private ConfigurablePasswordEncryptor passwordEncryptor;

	public User(String uID, String username, String password, String email, GroupType groupType){
	
		passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm("SHA-1");
		passwordEncryptor.setPlainDigest(true);
		
		if(uID == null || uID.isEmpty())
			this.uID= MD5Hash.getMD5Hash(username).toString();
		else
			this.uID = uID;
		
		this.username=username;
		this.password= passwordEncryptor.encryptPassword(password);
		this.email=email;
		this.groupType=groupType;
	}
	
	public User(){
		passwordEncryptor = new ConfigurablePasswordEncryptor();
	}

	
	public GroupType getGroupType() {
		return groupType;
	}

	public void setGroupType(GroupType groupType) {
		this.groupType = groupType;
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getuID() {
		return uID;
	}

	public void setuID(String uID) {
		this.uID = uID;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		//this.password = password;
		passwordEncryptor.setAlgorithm("SHA-1");
		passwordEncryptor.setPlainDigest(true);
		String encryptedPassword = passwordEncryptor.encryptPassword(password);
		this.password = encryptedPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
