package ch.zbw.sysVentorySaas.App2.model;

public class User {
	private int idUser;
	private String username;
	private String password;
	private String email;

	public User(String username, String password, String email) {
		this.username=username;
		this.password=password;
		this.email=email;
	}
	
	public User(){
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
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
