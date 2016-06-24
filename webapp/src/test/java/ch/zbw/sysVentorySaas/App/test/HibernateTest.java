package ch.zbw.sysVentorySaas.App.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.print.attribute.standard.MediaSize.Other;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import ch.zbw.sysVentorySaas.App.DataAccessObject.CompanyDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.DeviceDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.GroupDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.NetworkInterfaceDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.OperatingSystemDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.OtherDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.PrinterDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ProcessorDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ScanJobDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ScanSettingDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ScanStatusDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ServiceDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.SoftwareDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.UserDAO;
import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.helpers.MD5Hash;
import ch.zbw.sysVentorySaas.App.model.Company;
import ch.zbw.sysVentorySaas.App.model.Device;
import ch.zbw.sysVentorySaas.App.model.Group;
import ch.zbw.sysVentorySaas.App.model.NetworkInterface;
import ch.zbw.sysVentorySaas.App.model.OperatingSystem;
import ch.zbw.sysVentorySaas.App.model.Printer;
import ch.zbw.sysVentorySaas.App.model.Processor;
import ch.zbw.sysVentorySaas.App.model.ScanJob;
import ch.zbw.sysVentorySaas.App.model.ScanSetting;
import ch.zbw.sysVentorySaas.App.model.ScanStatus;
import ch.zbw.sysVentorySaas.App.model.Service;
import ch.zbw.sysVentorySaas.App.model.Software;
import ch.zbw.sysVentorySaas.App.model.User;

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
		
		/*user ="sysventory";
		password="Admin123";
		url="jdbc:sqlserver://sysventorysql.database.windows.net:1433";
		mySQLParams = ";database=SysventorySQL;user=sysventory@sysventorysql;password={Admin123};encrypt=true;trustServerCertificate=true;hostNameInCertificate=eastasia1-a.control.database.windows.net;loginTimeout=30;";
		*/
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
				System.out.println(e.getMessage());
				throw new SQLException(e.getMessage());
				
			}		
	}
	
	@Test
	public void TestSoftware_CRUD()
	{
		Software newSoftware = SoftwareDAO.createSoftware(new Software("NEST/IS-E", "InnoSolv AG", "2016.6"));
		
		assertEquals("InnoSolv AG", newSoftware.getManufacturer());
		assertEquals("NEST/IS-E", newSoftware.getName());
		assertEquals("2016.6", newSoftware.getVersion());
		
		Software softwareSelected = SoftwareDAO.getSoftwarebyId(newSoftware.getIdSoftware());
		assertEquals(newSoftware.getIdSoftware(), softwareSelected.getIdSoftware());
		
		SoftwareDAO.deleteSoftware(newSoftware);
		assertNull(SoftwareDAO.getSoftwarebyId(newSoftware.getIdSoftware()));	
	}
	
	@Test
	public void TestOperatingSystem_CRUD()
	{
		OperatingSystem op = new OperatingSystem("Windows 10", "Microsoft Windows", "64-bit");
		OperatingSystemDAO.createOperatingSystem(op);
		
		assertEquals("Windows 10", OperatingSystemDAO.getOperatingSystemById(1).getName());
		assertEquals("Microsoft Windows", OperatingSystemDAO.getOperatingSystemById(1).getManufacturer());
		assertEquals("64-bit", OperatingSystemDAO.getOperatingSystemById(1).getArchitecture());
		
		OperatingSystemDAO.deleteOperatingSystem(OperatingSystemDAO.getOperatingSystemById(1));
		assertNull(OperatingSystemDAO.getOperatingSystemById(1));		
	}
	
	@Test
	public void TestProcessor_CRUD()
	{
		Processor pr = new Processor("i7-465U", "Intel(R)",  4, "2.30 GHz");
		ProcessorDAO.createProcessor(pr);
		
		Processor prSelected = ProcessorDAO.getProcessorById(1);
		assertEquals("i7-465U", prSelected.getName());
		assertEquals("Intel(R)", prSelected.getManufacturer());
		assertEquals(4, prSelected.getCores());
		assertEquals("2.30 GHz", prSelected.getFrequency());
		
		ProcessorDAO.deleteProcessor(prSelected);
		assertNull(ProcessorDAO.getProcessorById(1));
	}
	
	@Test
	public void TestPrinter_CRUD(){
		Printer p = new Printer("Canon", "Farbdrucker Zimmer links");
		PrinterDAO.createPrinter(p);
		
		Printer pSelected = PrinterDAO.getPrinterById(1);
		assertEquals("Canon", pSelected.getName());
		assertEquals("Farbdrucker Zimmer links", pSelected.getDescription());
		
		PrinterDAO.deleteProcessor(pSelected);
		assertNull(PrinterDAO.getPrinterById(1));
	}
	
	@Test
	public void TestNetworkInterface_CRUD(){
		NetworkInterface ni = new NetworkInterface("Marvell AVASTAR Wireless-AC Network Controller");
		NetworkInterfaceDAO.createNetworkInterface(ni);
		
		NetworkInterface niSelected = NetworkInterfaceDAO.getNetworkInterfaceById(1);
		assertEquals("Marvell AVASTAR Wireless-AC Network Controller", niSelected.getName());
		
		NetworkInterfaceDAO.deleteNetworkInterface(niSelected);
		assertNull(NetworkInterfaceDAO.getNetworkInterfaceById(1));
	}
	
	@Test
	public void TestDevice_CRUD(){
		Device dev = new Device("Desktop PC", "DELL", "50-1A-C5-F4-C7-BB", "192.168.2.21");
		Device newDevice = DeviceDAO.createDevice(dev);
		
		Device devSelected = DeviceDAO.getDeviceById(newDevice.getIdDevice());
		assertEquals("Desktop PC", devSelected.getName());
		assertEquals("DELL", devSelected.getManufacturer());
		assertEquals("50-1A-C5-F4-C7-BB", devSelected.getMacAddress());
		assertEquals("192.168.2.21", devSelected.getIpAddress());
		
		DeviceDAO.deleteDevice(DeviceDAO.getDeviceById(devSelected.getIdDevice()));
		assertNull(DeviceDAO.getDeviceById(devSelected.getIdDevice()));
	}
	
	@Test
	public void TestUser_CRUD(){
		byte[] hash = MD5Hash.getMD5Hash("ruel85");	
		
		User user2 = new User("2d1a0484f40daceeef42967c4ac00911", "ruel85", "12345", "ruel.holderegger@gmx.ch");
	
		User user3 = new User(null, "elias", "12345", null);
		User user4 = new User(null, "saj", "12345", null);
		User user5 = new User(null, "damjan", "12345", null);	
		
		Company comp = new Company("ZbW", "Gaiserwaldstrasse", "1", null, "9043", "Abtwil SG");
		CompanyDAO.createCompany(comp);
		user2.setCompany(comp);
		user3.setCompany(comp);
		user4.setCompany(comp);
		user5.setCompany(comp);
	
		UserDAO.createUser(user2);
		//ScanSetting sc = OtherDAO.getScanSettingByUID("2d1a0484f40daceeef42967c4ac00911");
		
		UserDAO.createUser(user3);
		UserDAO.createUser(user4);
		UserDAO.createUser(user5);
		
		User userSelected = UserDAO.getUserByIdUser(user2.getIdUser());
		//assertEquals(newUser.getuID(), userSelected.getuID());
		
		assertEquals("ruel85", user2.getUsername());
		assertEquals("12345", user2.getPassword());
		assertEquals("ruel.holderegger@gmx.ch", user2.getEmail());
		
		ScanSetting scanSetting = new ScanSetting("ZBW-Gast", "192.168.5.55", "192.168.70.30", 60, true);
		
		comp.setScanSetting(scanSetting);
		scanSetting.setCompany(comp);
		CompanyDAO.createCompany(comp);
		
		//userDAO.deleteUser(newUser);
		//assertNull(userDAO.getUserByIdUser(newUser.getIdUser()));
	}
	
	@Test
	public void TestGroup_CRUD(){
		Group newGroup = GroupDAO.createGroup( new Group("CustomerAdmin"));
		
		GroupDAO.createGroup(new Group("SysVentoryAdmin"));
		GroupDAO.createGroup(new Group("Inventor"));
		GroupDAO.createGroup(new Group("Visitor"));
		
		Group groupSelected = GroupDAO.getGroupById(newGroup.getIdGroup());
		assertEquals(groupSelected.getIdGroup(), newGroup.getIdGroup());
		assertEquals(newGroup.getName(), groupSelected.getName());
		
		GroupDAO.deleteGroup(newGroup);
		assertNull(GroupDAO.getGroupById(newGroup.getIdGroup()));
	}
	
	@Test
	public void TestCompany_CRUD_AND_TestScanSetting(){
		Company comp = new Company("InnoSolv AG", "Ikarusstrasse", "9", null, "9015", "St. Gallen");	;
		ScanSetting scanSetting = new ScanSetting("DonRaul", "192.168.1.1", "192.168.1.35", 1, false);
		
		comp.setScanSetting(scanSetting);
		scanSetting.setCompany(comp);
		comp = CompanyDAO.createCompany(comp);
		// Prüfen, ob 1-1 Beziehung wirklich vorhanden ist...
		assertEquals(comp.getIdCompany(), ScanSettingDAO.getScanSettingById(comp.getIdCompany()).getIdCompany());
		
		// 2. Datensatz (ohne ScanSetting) einfügen...
		comp = new Company("Movento", "Irgendeinestrasse", "10", null, "9015", "St. Gallen");
		CompanyDAO.createCompany(comp);
		assertNull(ScanSettingDAO.getScanSettingById(comp.getIdCompany()));
		
		//.. und diese Company ohne ScanSetting gleich wieder löschen
		CompanyDAO.deleteCompany(CompanyDAO.getCompanyById(2));
		assertNull(CompanyDAO.getCompanyById(2));
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		ScanSetting scanSetting1 = new ScanSetting("DonRaul", "192.168.1.1", "192.168.1.35", 1, false);
		Company comp1 = new Company("VRSG", "Herisauerstrasse", "123", null, "9015", "St. Gallen");
		comp1.setScanSetting(scanSetting1);
		scanSetting1.setCompany(comp1);
		session.save(comp1);
		
		List<Company> companies = session.createQuery("from Company").list();
		for (Company company : companies) {
			System.out.println(company.getName() + " , "
					+ company.getStreet());
		}
		session.getTransaction().commit();
		session.close();		
		
		List<ScanSetting> list = ScanSettingDAO.getAllScanSettings();
			for (ScanSetting oneScanSetting : list) {
				if (oneScanSetting != null)
					System.out.println("ScanSetting with idCompany:" + oneScanSetting.getIdCompany()); 
			}
	}
	
	@Test
	public void TestService_CRUD(){
		Service serv = new Service("MyService", true);
		ServiceDAO.createService(serv);
		
		Service servSelected = ServiceDAO.getServiceById(1);
		assertEquals("MyService", servSelected.getName());
		assertEquals(true, servSelected.getEnabled());
		
		ServiceDAO.deleteCompany(ServiceDAO.getServiceById(1));
		assertNull(ServiceDAO.getServiceById(1));
	}
	
	@Test
	public void TestScanStatus (){
		ScanStatus scanStatus = new ScanStatus("Vorbereitet", "Der Scan ist vorbereitet und kann verarbeitet werden.");
		ScanStatusDAO.createScanStatus(scanStatus);
		
		ScanStatus scanStatusSelected = ScanStatusDAO.getScanStatusById(1);
		assertEquals("Vorbereitet", scanStatusSelected.getName());
		assertEquals("Der Scan ist vorbereitet und kann verarbeitet werden.", scanStatusSelected.getDescription());
		
		ScanStatusDAO.deleteScanStatus(ScanStatusDAO.getScanStatusById(1));
		assertNull(ScanStatusDAO.getScanStatusById(1));
	}
	
	@Test
	public void TestScanJob(){
		ScanJob scanJ = new ScanJob("07:00", "07:30", new ScanStatus("Erledigt", "Irgendeine Beschreibung"));
		ScanJob newScanJob = ScanJobDAO.createScanJob(scanJ);
		
		ScanJob scanJobSelected = ScanJobDAO.getScanJobById(newScanJob.getIdScanJob());
		assertEquals("07:00", scanJobSelected.getStartTime());
		assertEquals("07:30", scanJobSelected.getEndTime());
		
		//Todo Ruel: Daten können nicht geladen werden --> DB-Relation korrekt setzen in Hibernate!
		//assertEquals("Erledigt", scanJobSelected.getStatus().getName());
		//assertEquals("Irgendeine Beschreibung", scanJobSelected.getStatus().getDescription());
		
		ScanJobDAO.deleteScanJob(scanJobSelected);
		assertNull(ScanJobDAO.getScanJobById(scanJobSelected.getIdScanJob()));
	}
	
	@Test
	public void Test_Company_And_User(){
		//Company comp1 = cDAO.createCompany(new Company("Siemens", "Hauptstrasse", "20", "a", "9050", "Appenzell"));
		//Company comp2 = cDAO.createCompany(new Company("Bühler AG", "Hauptstrasse", "25", "b", "9240", "Uzwil SG"));
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Company comp1 = new Company("Siemens", "Hauptstrasse", "20", "a", "9050", "Appenzell");
		Company comp2 = new Company("Bühler AG", "Hauptstrasse", "25", "b", "9240", "Uzwil SG");
		session.save(comp1);
		session.save(comp2);
		
		//User user1 = UserDAO.createUser(new User("Ruelito", "rtwoirptow", "ruel.holderegger@outlook.com"));
		//User user2 = UserDAO.createUser(new User("Kevin", "0000", "info@info.de"));
		
		User user1 = new User(null, "Ruelito", "rtwoirptow", "ruel.holderegger@outlook.com");
		User user2 = new User(null, "Kevin", "0000", "info@info.de");
		
		user1.setCompany(comp1);
		session.save(user1);
		
		user2.setCompany(comp2);
		session.save(user2);
		
		session.getTransaction().commit();	
	}
}
