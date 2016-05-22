package ch.zbw.sysVentorySaas.App2;

import static org.junit.Assert.*;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import ch.zbw.sysVentorySaas.App2.DataAccessObject.OperatingSystemDAO;
import ch.zbw.sysVentorySaas.App2.DataAccessObject.SoftwareDAO;
import ch.zbw.sysVentorySaas.App2.model.OperatingSystem;
import ch.zbw.sysVentorySaas.App2.model.Software;

public class HibernateTest {
	private String user;
	private String password;
	private String url;
	private String mySQLParams;
	private Connection con;
	private String st;
	
	@Before
	public void setUp(){
		
		user ="SysVentoryAdmin";
		password="vdjjmf#n$ri7cr!?+RX7ZVbY5";
		url="jdbc:mysql://ruelholderegger.ch:3306/SysVentorySaas";
		mySQLParams = "?useSSL=false&serverTimezone=Europe/Paris";
	}
	
	@Test
	public void TestGeneral_MySQLDB_Hoststar() throws SQLException{
			try {
				con = DriverManager.getConnection(url + mySQLParams, user, password);
				st = "Show databases;";
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(st);
				rs.next();
				//System.out.println(rs.getString(1));
				con.close();
				
			} catch (SQLException e) {
				throw new SQLException(e.getMessage());
			}
	}
	
	@Test
	public void TestSoftware_CRUD()
	{
		Software software = new Software("NEST/IS-E", "InnoSolv AG", "2016.6");
		SoftwareDAO softwareDAO = new SoftwareDAO();
		softwareDAO.createSoftware(software);
		
		assertEquals("InnoSolv AG", softwareDAO.getSoftwarebyId(1).getManufacturer());
		assertEquals("NEST/IS-E", softwareDAO.getSoftwarebyId(1).getName());
		assertEquals("2016.6", softwareDAO.getSoftwarebyId(1).getVersion());
		
		softwareDAO.deleteSoftware(softwareDAO.getSoftwarebyId(1));
		assertNull(softwareDAO.getSoftwarebyId(1));	
	}
	
	@Test
	public void TestOperatingSystem_CRUD()
	{
		OperatingSystem op = new OperatingSystem("Windows 10", "Microsoft Windows", "64-bit");
		OperatingSystemDAO opDAO = new OperatingSystemDAO();
		opDAO.createOperatingSystem(op);
		
		assertEquals("Windows 10", opDAO.getOperatingSystemById(1).getName());
		assertEquals("Microsoft Windows", opDAO.getOperatingSystemById(1).getManufacturer());
		assertEquals("64-bit", opDAO.getOperatingSystemById(1).getArchitecture());
		
		opDAO.deleteOperatingSystem(opDAO.getOperatingSystemById(1));
		assertNull(opDAO.getOperatingSystemById(1));		
	}
}
