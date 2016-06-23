package ch.zbw.sysVentorySaas.App.model;
import java.util.List;
import ch.zbw.sysVentorySaas.App.helpers.MD5Hash;

public class User {
	private int idUser;
	private String uID;
	private String username;
	private String password;
	private String email;
	
	private List<Group> groups;
	
	private Company company;

	public User(String uID, String username, String password, String email){
		if(uID.isEmpty() || uID == null)
		{
			this.uID= MD5Hash.getMD5Hash(username).toString();
		}
		this.uID = uID;
		this.username=username;
		this.password=password;
		this.email=email;
	}
	
	public User(){
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
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
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
