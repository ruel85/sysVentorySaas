package ch.zbw.sysVentorySaas.App.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.junit.Before;
import org.junit.Test;
import ch.zbw.sysVentorySaas.App.DataAccessObject.CompanyDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.DeviceDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.GroupDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.NetworkInterfaceDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.OperatingSystemDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.PrinterDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.PrinterDriverDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ProcessorDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.SIDDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ScanJobDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ScanSettingDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ScanStatusDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ServiceDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.SoftwareDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.UserDAO;
import ch.zbw.sysVentorySaas.App.helpers.GroupType;
import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.helpers.JobStatus;
import ch.zbw.sysVentorySaas.App.helpers.PasswordEncryptor;
import ch.zbw.sysVentorySaas.App.model.Company;
import ch.zbw.sysVentorySaas.App.model.Device;
import ch.zbw.sysVentorySaas.App.model.Group;
import ch.zbw.sysVentorySaas.App.model.NetworkInterface;
import ch.zbw.sysVentorySaas.App.model.OperatingSystem;
import ch.zbw.sysVentorySaas.App.model.Printer;
import ch.zbw.sysVentorySaas.App.model.PrinterDriver;
import ch.zbw.sysVentorySaas.App.model.Processor;
import ch.zbw.sysVentorySaas.App.model.SID;
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
		url="jdbc:mysql://ruelholderegger.ch:3306/SysVentorySaas03";
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
		ScanJob scanjob = ScanJobDAO.getAllScanJobs().get(0);
		Device newDevice = new Device("Juhuu", "ZbW", "654658", "1654.65.5645", "6544", "65461s");
		newDevice.setScanJob(scanjob);
		DeviceDAO.saveDevice(newDevice);
		
		Software newSoftware = new Software("NEST/IS-E", "InnoSolv AG", "2016.6");
		newSoftware.setDevice(newDevice);
		newSoftware = SoftwareDAO.saveSoftware(newSoftware);
		
		assertEquals("InnoSolv AG", newSoftware.getManufacturer());
		assertEquals("NEST/IS-E", newSoftware.getName());
		assertEquals("2016.6", newSoftware.getVersion());
		
		Software softwareSelected = SoftwareDAO.getSoftwarebyId(newSoftware.getIdSoftware());
		assertEquals(newSoftware.getIdSoftware(), softwareSelected.getIdSoftware());
		
		//SoftwareDAO.deleteSoftware(newSoftware);
		//assertNull(SoftwareDAO.getSoftwarebyId(newSoftware.getIdSoftware()));	
	}
	
	@Test
	public void TestOperatingSystem_CRUD()
	{
		OperatingSystem op = new OperatingSystem("Windows 10", "Microsoft Windows", "64-bit");
		OperatingSystem newOP = OperatingSystemDAO.saveOperatingSystem(op);
		
		assertEquals("Windows 10", OperatingSystemDAO.getOperatingSystemById(newOP.getIdOperatingSystem()).getName());
		assertEquals("Microsoft Windows", OperatingSystemDAO.getOperatingSystemById(newOP.getIdOperatingSystem()).getManufacturer());
		assertEquals("64-bit", OperatingSystemDAO.getOperatingSystemById(newOP.getIdOperatingSystem()).getArchitecture());
		
		OperatingSystemDAO.deleteOperatingSystem(newOP);
		assertNull(OperatingSystemDAO.getOperatingSystemById(newOP.getIdOperatingSystem()));		
	}
	
	@Test
	public void TestProcessor_CRUD()
	{
		Processor pr = new Processor("i7-465U", "Intel(R)",  4, "2.30 GHz");
		Processor newProcessor = ProcessorDAO.saveProcessor(pr);
		
		Processor prSelected = ProcessorDAO.getProcessorById(newProcessor.getIdProcessor());
		assertEquals("i7-465U", prSelected.getName());
		assertEquals("Intel(R)", prSelected.getManufacturer());
		assertEquals(4, prSelected.getCores());
		assertEquals("2.30 GHz", prSelected.getFrequency());
		
		ProcessorDAO.deleteProcessor(prSelected);
		assertNull(ProcessorDAO.getProcessorById(prSelected.getIdProcessor()));
	}
	
	@Test
	public void TestPrinter_CRUD(){
		Printer p = new Printer("Canon", "Farbdrucker Zimmer links");
		Printer newPrinter = PrinterDAO.savePrinter(p);
		
		Printer pSelected = PrinterDAO.getPrinterById(newPrinter.getIdPrinter());
		assertEquals("Canon", pSelected.getName());
		assertEquals("Farbdrucker Zimmer links", pSelected.getDescription());
		
		PrinterDAO.deleteProcessor(pSelected);
		assertNull(PrinterDAO.getPrinterById(newPrinter.getIdPrinter()));
	}
	
	@Test
	public void TestNetworkInterface_CRUD(){
		NetworkInterface ni = new NetworkInterface(
				"Marvell AVASTAR Wireless-AC Network Controller","10.0.20.17", "255.255.255.0", "10.1.53.1", "34:E6:D7:4B:AC:A1s");
		NetworkInterfaceDAO.saveNetworkInterface(ni);
		
		NetworkInterface niSelected = NetworkInterfaceDAO.getNetworkInterfaceById(ni.getIdNetworkInterface());
		assertEquals("Marvell AVASTAR Wireless-AC Network Controller", niSelected.getName());
		assertEquals("10.0.20.17", niSelected.getDhcp());
		assertEquals("255.255.255.0", niSelected.getSubnet());
		assertEquals("10.1.53.1", niSelected.getGateway());
		assertEquals("34:E6:D7:4B:AC:A1s", niSelected.getMacAddress());
		
		NetworkInterfaceDAO.deleteNetworkInterface(niSelected);
		assertNull(NetworkInterfaceDAO.getNetworkInterfaceById(niSelected.getIdNetworkInterface()));
	}
	
	@Test
	public void TestDevice_CRUD(){
		
		ScanJob scanJob = ScanJobDAO.getAllScanJobs().get(0);
		
		Device dev = new Device("Desktop PC", "DELL", "50-1A-C5-F4-C7-BB", "192.168.2.21", "65434", "x64-based PC");
		dev.setScanJob(scanJob);
		Device newDevice = DeviceDAO.saveDevice(dev);
		
		Device devSelected = DeviceDAO.getDeviceById(newDevice.getIdDevice());
		assertEquals("Desktop PC", devSelected.getName());
		assertEquals("DELL", devSelected.getManufacturer());
		assertEquals("50-1A-C5-F4-C7-BB", devSelected.getMacAddress());
		assertEquals("192.168.2.21", devSelected.getIpAddress());
		assertEquals("65434", devSelected.getMemory());
		assertEquals("x64-based PC", devSelected.getSystemType());
		
		DeviceDAO.deleteDevice(DeviceDAO.getDeviceById(devSelected.getIdDevice()));
		assertNull(DeviceDAO.getDeviceById(devSelected.getIdDevice()));
	}
	
	@Test
	public void TestUser_CRUD(){		
		ConfigurablePasswordEncryptor encryptor = PasswordEncryptor.getPWEncryptor();
		Company comp = new Company("ZbW", "Gaiserwaldstrasse", "1", null, "9043", "Abtwil SG");
		CompanyDAO.saveCompany(comp);
		
		List<User> userList = new ArrayList<User>();
		userList.add(new User("2d1a0484f40daceeef42967c4ac00911", "ruel85", "12345", "ruel.holderegger@gmx.ch", GroupType.SysVentoryAdmin));
		userList.add( new User(null, "elias", "12345", null, GroupType.SysVentoryAdmin));
		userList.add(new User(null, "saj", "12345", null, GroupType.SysVentoryAdmin));
		userList.add( new User(null, "damjan", "12345", null, GroupType.SysVentoryAdmin));
		userList.add( new User(null, "palmer", "password", null, GroupType.CustomerAdmin));
		userList.add( new User(null, "max muster", "muster", null, GroupType.CustomerAdmin));
		
		
		for(User oneUser : userList)
		{
			oneUser.setCompany(comp);
			UserDAO.saveUser(oneUser);
		}
		
		User userSelected = userList.get(0);
		assertEquals("ruel85", userSelected.getUsername());
		assertTrue(encryptor.checkPassword("12345", userSelected.getPassword()));
		assertEquals("ruel.holderegger@gmx.ch", userSelected.getEmail());
		assertEquals(GroupType.SysVentoryAdmin, userSelected.getGroupType());
		
		ScanSetting scanSetting = new ScanSetting("ZBW-Gast", "192.168.5.55", "192.168.70.30", 60, true);		
		comp.setScanSetting(scanSetting);
		scanSetting.setCompany(comp);
		CompanyDAO.saveCompany(comp);
		
		//userDAO.deleteUser(newUser);
		//assertNull(userDAO.getUserByIdUser(newUser.getIdUser()));
	}
	
	@Test
	public void TestCompany_CRUD_AND_TestScanSetting() throws Exception{
		Company comp = new Company("InnoSolv AG", "Ikarusstrasse", "9", null, "9015", "St. Gallen");
		ScanSetting scanSetting = new ScanSetting("DonRaul", "192.168.1.1", "192.168.1.35", 1, false);
		
		comp.setScanSetting(scanSetting);
		scanSetting.setCompany(comp);
		comp = CompanyDAO.saveCompany(comp);

		
		// Prüfen, ob 1-1 Beziehung wirklich vorhanden ist...
		assertEquals(comp.getIdCompany(), ScanSettingDAO.getScanSettingById(comp.getIdCompany()).getIdCompany());
		
		// 2. Datensatz (ohne ScanSetting) einfügen...
		comp = new Company("Movento", "Irgendeinestrasse", "10", null, "9015", "St. Gallen");
		Company newCompany = CompanyDAO.saveCompany(comp);

		//.. und diese Company ohne ScanSetting gleich wieder löschen
		CompanyDAO.deleteCompany(CompanyDAO.getCompanyById(newCompany.getIdCompany()));
		assertNull(CompanyDAO.getCompanyById(newCompany.getIdCompany()));
		
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
		Service newService = ServiceDAO.saveService(serv);
		
		Service servSelected = ServiceDAO.getServiceById(newService.getIdService());
		assertEquals("MyService", servSelected.getName());
		assertEquals(true, servSelected.getEnabled());
		
		ServiceDAO.deleteCompany(ServiceDAO.getServiceById(newService.getIdService()));
		assertNull(ServiceDAO.getServiceById(newService.getIdService()));
	}
	
	@Test
	public void TestScanStatus (){
		ScanStatus scanStatus = new ScanStatus("Vorbereitet", "Der Scan ist vorbereitet und kann verarbeitet werden.");
		ScanStatusDAO.saveScanStatus(scanStatus);
		
		ScanStatus scanStatusSelected = ScanStatusDAO.getScanStatusById(1);
		assertEquals("Vorbereitet", scanStatusSelected.getName());
		assertEquals("Der Scan ist vorbereitet und kann verarbeitet werden.", scanStatusSelected.getDescription());
		
		ScanStatusDAO.deleteScanStatus(ScanStatusDAO.getScanStatusById(1));
		assertNull(ScanStatusDAO.getScanStatusById(1));
	}
	
	@Test
	public void TestScanJob(){
		
		ScanSetting sc = ScanSettingDAO.getAllScanSettings().get(0);
		
		ScanJob scanJ = new ScanJob("07:00", "07:30", JobStatus.Erstellt
				, sc.getNetworkName(), sc.getIpStart(), sc.getIpEnd(), sc.getIntervallMinutes(), sc);
		ScanJob newScanJob = ScanJobDAO.saveScanJob(scanJ);
		
		ScanJob scanJobSelected = ScanJobDAO.getScanJobById(newScanJob.getIdScanJob());
		assertEquals("07:00", scanJobSelected.getStartTime());
		assertEquals("07:30", scanJobSelected.getEndTime());
		assertEquals(JobStatus.Erstellt, scanJobSelected.getJobStatus());
		
		//ScanJobDAO.deleteScanJob(scanJobSelected);
		//assertNull(ScanJobDAO.getScanJobById(scanJobSelected.getIdScanJob()));
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
		
		User user1 = new User(null, "Ruelito", "rtwoirptow", "ruel.holderegger@outlook.com", GroupType.Visitor);
		User user2 = new User(null, "Kevin", "0000", "info@info.de", GroupType.Inventor);
		
		user1.setCompany(comp1);
		session.save(user1);
		
		user2.setCompany(comp2);
		session.save(user2);
		
		session.getTransaction().commit();	
	}
	
	@Test
	public void Test_SID(){
		SID sid = SIDDAO.saveSID(new SID("S-1-5-21-2056415622-1170722248-999543400-501"));
		
		SID sidSelected = SIDDAO.getSIDById(sid.getIdSID());
		assertEquals("S-1-5-21-2056415622-1170722248-999543400-501", sidSelected.getSID());
	}
	
	@Test
	public void Test_PrinterDriver()
	{
		PrinterDriver printerDriver = PrinterDriverDAO.savePrinterDriver(new PrinterDriver("PDF-XChange 4.0,3,Windows x64"));
		PrinterDriver printerDriverSelected = PrinterDriverDAO.getPrinterDriverById(printerDriver.getIdPrinterDriver());
		assertEquals("PDF-XChange 4.0,3,Windows x64", printerDriverSelected.getName());
		
		PrinterDriverDAO.deletePrinterDriver(printerDriverSelected);
		assertNull(PrinterDriverDAO.getPrinterDriverById(printerDriverSelected.getIdPrinterDriver()));
	}
	
	
}
