package ch.zbw.sysVentorySaas.App.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class Company implements java.io.Serializable{
	
	@Id @GeneratedValue
	private int idCompany;
	
	private String name;
	private String street;
	private String houseNumber;
	private String houseNumberAdd;
	private String zipCode;
	private String city;
	
	private ScanSetting scanSetting;
	
	private List<User> users;
	
	public Company(String name, String street, String houseNumber, String houseNumberAdd, String zipCode, String city) {
		this.name=name;
		this.street=street;
		this.houseNumber=houseNumber;
		this.houseNumberAdd=houseNumberAdd;
		this.zipCode=zipCode;
		this.city=city;
	}

	public Company() {
	}

	public int getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getHouseNumberAdd() {
		return houseNumberAdd;
	}

	public void setHouseNumberAdd(String houseNumberAdd) {
		this.houseNumberAdd = houseNumberAdd;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public ScanSetting getScanSetting() {
		return scanSetting;
	}

	public void setScanSetting(ScanSetting scanSetting) {
		this.scanSetting = scanSetting;
	}
}
