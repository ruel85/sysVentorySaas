package ch.zbw.sysVentorySaas.App2;

import static org.junit.Assert.*;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import org.junit.Before;
import org.junit.Test;

import ch.zbw.sysVentorySaas.App2.DataAccessObject.CompanyDAO;
import ch.zbw.sysVentorySaas.App2.DataAccessObject.DeviceDAO;
import ch.zbw.sysVentorySaas.App2.DataAccessObject.GroupDAO;
import ch.zbw.sysVentorySaas.App2.DataAccessObject.NetworkInterfaceDAO;
import ch.zbw.sysVentorySaas.App2.DataAccessObject.OperatingSystemDAO;
import ch.zbw.sysVentorySaas.App2.DataAccessObject.PrinterDAO;
import ch.zbw.sysVentorySaas.App2.DataAccessObject.ProcessorDAO;
import ch.zbw.sysVentorySaas.App2.DataAccessObject.ScanSettingDAO;
import ch.zbw.sysVentorySaas.App2.DataAccessObject.ScanStatusDAO;
import ch.zbw.sysVentorySaas.App2.DataAccessObject.ServiceDAO;
import ch.zbw.sysVentorySaas.App2.DataAccessObject.SoftwareDAO;
import ch.zbw.sysVentorySaas.App2.DataAccessObject.UserDAO;
import ch.zbw.sysVentorySaas.App2.model.Company;
import ch.zbw.sysVentorySaas.App2.model.Device;
import ch.zbw.sysVentorySaas.App2.model.Group;
import ch.zbw.sysVentorySaas.App2.model.NetworkInterface;
import ch.zbw.sysVentorySaas.App2.model.OperatingSystem;
import ch.zbw.sysVentorySaas.App2.model.Printer;
import ch.zbw.sysVentorySaas.App2.model.Processor;
import ch.zbw.sysVentorySaas.App2.model.ScanSetting;
import ch.zbw.sysVentorySaas.App2.model.ScanStatus;
import ch.zbw.sysVentorySaas.App2.model.Service;
import ch.zbw.sysVentorySaas.App2.model.Software;
import ch.zbw.sysVentorySaas.App2.model.User;

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
		
		devDAO.deleteDevice(devDAO.getDeviceById(1));
		assertNull(devDAO.getDeviceById(1));
	}
	
	@Test
	public void TestUser_CRUD(){
		User user = new User("ruel85", "12345", "ruel.holderegger@gmx.ch");
		UserDAO userDAO = new UserDAO();
		userDAO.createUser(user);
		
		User userSelected = userDAO.getUserbyId(1);
		assertEquals("ruel85", userSelected.getUsername());
		assertEquals("12345", userSelected.getPassword());
		assertEquals("ruel.holderegger@gmx.ch", userSelected.getEmail());
		
		userDAO.deleteUser(userDAO.getUserbyId(1));
		assertNull(userDAO.getUserbyId(1));
	}
	
	@Test
	public void TestGroup_CRUD(){
		Group group = new Group("Administratoren");
		GroupDAO groupDAO = new GroupDAO();
		groupDAO.createGroup(group);
		
		Group groupSelected = groupDAO.getGroupById(1);
		assertEquals("Administratoren", groupSelected.getName());
		
		groupDAO.deleteGroup(groupDAO.getGroupById(1));
		assertNull(groupDAO.getGroupById(1));
	}
	
	@Test
	public void TestCompany_CRUD(){
		Company comp = new Company("InnoSolv AG", "Ikarusstrasse", "9", null, "9015", "St. Gallen");
		CompanyDAO compDAO = new CompanyDAO();
		compDAO.createCompany(comp);
		
		Company compSelected = compDAO.getCompanyById(1);
		assertEquals("InnoSolv AG", compSelected.getName());
		assertEquals("Ikarusstrasse", compSelected.getStreet());
		assertEquals("9", compSelected.getHouseNumber());
		assertEquals(null, compSelected.getHouseNumberAdd());
		assertEquals("9015", compSelected.getZipCode());
		assertEquals("St. Gallen", compSelected.getCity());
		
		compDAO.deleteCompany(compDAO.getCompanyById(1));
		assertNull(compDAO.getCompanyById(1));
	}
	
	@Test
	public void TestService_CRUD(){
		Service serv = new Service("MyService", true);
		ServiceDAO servDAO = new ServiceDAO();
		servDAO.createService(serv);
		
		Service servSelected = servDAO.getServiceById(1);
		assertEquals("MyService", servSelected.getName());
		assertEquals(true, servSelected.getEnabled());
		
		servDAO.deleteCompany(servDAO.getServiceById(1));
		assertNull(servDAO.getServiceById(1));
	}
	
	@Test
	public void TestScanStatus (){
		ScanStatus scanStatus = new ScanStatus("Vorbereitet", "Der Scan ist vorbereitet und kann verarbeitet werden.");
		ScanStatusDAO scanStatusDAO = new ScanStatusDAO();
		scanStatusDAO.createScanStatus(scanStatus);
		
		ScanStatus scanStatusSelected = scanStatusDAO.getScanStatusById(1);
		assertEquals("Vorbereitet", scanStatusSelected.getName());
		assertEquals("Der Scan ist vorbereitet und kann verarbeitet werden.", scanStatusSelected.getDescription());
		
		scanStatusDAO.deleteScanStatus(scanStatusDAO.getScanStatusById(1));
		assertNull(scanStatusDAO.getScanStatusById(1));
	}
	
	@Test
	public void TestScanSetting(){
		ScanSetting scanS= new ScanSetting("HOLDEREGGER", "192.168.1.1", "192.168.1.35", "07:00", 1);
		ScanSettingDAO scanSDAO = new ScanSettingDAO();
		scanSDAO.createScanSetting(scanS);
		
		ScanSetting scanSettingSelected = scanSDAO.getScanSettingById(1);
		assertEquals("HOLDEREGGER", scanSettingSelected.getNetworkName());
		assertEquals("192.168.1.1", scanSettingSelected.getIpStart());
		assertEquals("192.168.1.35", scanSettingSelected.getIpEnd());
		assertEquals("07:00", scanSettingSelected.getStartTime());
		assertEquals(1, scanSettingSelected.getIntervallHours());
		
		scanSDAO.deleteScanSettings(scanSDAO.getScanSettingById(1));
		assertNull(scanSDAO.getScanSettingById(1));	
	}
}
