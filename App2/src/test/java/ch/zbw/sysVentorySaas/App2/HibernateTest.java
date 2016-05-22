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

import ch.zbw.sysVentorySaas.App2.DataAccessObject.DeviceDAO;
import ch.zbw.sysVentorySaas.App2.DataAccessObject.NetworkInterfaceDAO;
import ch.zbw.sysVentorySaas.App2.DataAccessObject.OperatingSystemDAO;
import ch.zbw.sysVentorySaas.App2.DataAccessObject.PrinterDAO;
import ch.zbw.sysVentorySaas.App2.DataAccessObject.ProcessorDAO;
import ch.zbw.sysVentorySaas.App2.DataAccessObject.SoftwareDAO;
import ch.zbw.sysVentorySaas.App2.model.Device;
import ch.zbw.sysVentorySaas.App2.model.NetworkInterface;
import ch.zbw.sysVentorySaas.App2.model.OperatingSystem;
import ch.zbw.sysVentorySaas.App2.model.Printer;
import ch.zbw.sysVentorySaas.App2.model.Processor;
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
	
	@Test
	public void TestProcessor_CRUD()
	{
		Processor pr = new Processor("i7-465U", "Intel(R)",  4, "2.30 GHz");
		ProcessorDAO prDAO = new ProcessorDAO();
		prDAO.createProcessor(pr);
		
		Processor prSelected = prDAO.getProcessorById(1);
		assertEquals("i7-465U", prSelected.getName());
		assertEquals("Intel(R)", prSelected.getManufacturer());
		assertEquals(4, prSelected.getCores());
		assertEquals("2.30 GHz", prSelected.getFrequency());
		
		prDAO.deleteProcessor(prSelected);
		assertNull(prDAO.getProcessorById(1));
	}
	
	@Test
	public void TestPrinter_CRUD(){
		Printer p = new Printer("Canon", "Farbdrucker Zimmer links");
		PrinterDAO pDAO = new PrinterDAO();
		pDAO.createPrinter(p);
		
		Printer pSelected = pDAO.getPrinterById(1);
		assertEquals("Canon", pSelected.getName());
		assertEquals("Farbdrucker Zimmer links", pSelected.getDescription());
		
		pDAO.deleteProcessor(pSelected);
		assertNull(pDAO.getPrinterById(1));
	}
	
	@Test
	public void TestNetworkInterface_CRUD(){
		NetworkInterface ni = new NetworkInterface("Marvell AVASTAR Wireless-AC Network Controller");
		NetworkInterfaceDAO niDAO = new NetworkInterfaceDAO();
		niDAO.createNetworkInterface(ni);
		
		NetworkInterface niSelected = niDAO.getNetworkInterfaceById(1);
		assertEquals("Marvell AVASTAR Wireless-AC Network Controller", niSelected.getName());
		
		niDAO.deleteNetworkInterface(niSelected);
		assertNull(niDAO.getNetworkInterfaceById(1));
	}
	
	@Test
	public void TestDevice_CRUD(){
		Device dev = new Device("Desktop PC", "DELL", "50-1A-C5-F4-C7-BB", "192.168.2.21");
		DeviceDAO devDAO = new DeviceDAO();
		devDAO.createDevice(dev);
		
		Device devSelected = devDAO.getDeviceById(1);
		assertEquals("Desktop PC", devSelected.getName());
		assertEquals("DELL", devSelected.getManufacturer());
		assertEquals("50-1A-C5-F4-C7-BB", devSelected.getMacAddress());
		assertEquals("192.168.2.21", devSelected.getIpAddress());
		
		//devDAO.deleteDevice(devDAO.getDeviceById(1));
		//assertNull(devDAO.getDeviceById(1));
	}
}
