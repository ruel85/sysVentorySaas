package ch.zbw.sysVentorySaas.App2.model;

import javax.persistence.*;

@Entity
public class Software {
	
	@Id
	@Column(name="idSoftware")
	private int idSoftware;
	
	@Column(name="name")
	private String name;
	@Column(name="manufacturer")
	private String manufacturer;
	@Column(name="version")
	private String version;
	
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId_software() {
		return idSoftware;
	}
	public void setId_software(int id_software) {
		this.idSoftware = id_software;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
