package ch.zbw.sysVentorySaas.App.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import ch.zbw.sysVentorySaas.App.DataAccessObject.CompanyDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.DeviceDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.GroupDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.NetworkInterfaceDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.OperatingSystemDAO;
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
		
		/*SessionFactory sessionFactory;
		
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		try {
			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		}
		catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy( registry );
		}*/
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
		SoftwareDAO softwareDAO = new SoftwareDAO();
		Software newSoftware = softwareDAO.createSoftware(new Software("NEST/IS-E", "InnoSolv AG", "2016.6"));
		
		assertEquals("InnoSolv AG", newSoftware.getManufacturer());
		assertEquals("NEST/IS-E", newSoftware.getName());
		assertEquals("2016.6", newSoftware.getVersion());
		
		Software softwareSelected = softwareDAO.getSoftwarebyId(newSoftware.getIdSoftware());
		assertEquals(newSoftware.getIdSoftware(), softwareSelected.getIdSoftware());
		
		softwareDAO.deleteSoftware(newSoftware);
		assertNull(softwareDAO.getSoftwarebyId(newSoftware.getIdSoftware()));	
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
	
	@Test
	public void TestGetDevices()
	{
		DeviceDAO deviceDAO = new DeviceDAO();
		List<Device> devices = deviceDAO.getDevicesByScanJob(1);
		
		assertEquals(1, devices.size());
	}
	
	@Test
	public void TestUser_CRUD(){
		User newUser = null;
		byte[] hash = MD5Hash.getMD5Hash("ruel85");	
		
		UserDAO userDAO = new UserDAO();
		newUser = userDAO.createUser(new User("ruel85", "12345", "ruel.holderegger@gmx.ch"));
		//newUser = userDAO.createUser(new User("2d1a0484f40daceeef42967c4ac00911", "ruel85", "12345", "ruel.holderegger@gmx.ch"));
				
		User userSelected = userDAO.getUserByIdUser(newUser.getIdUser());
		//assertEquals(newUser.getuID(), userSelected.getuID());
		
		assertEquals("ruel85", newUser.getUsername());
		assertEquals("12345", newUser.getPassword());
		assertEquals("ruel.holderegger@gmx.ch", newUser.getEmail());
		
		//userDAO.deleteUser(newUser);
		//assertNull(userDAO.getUserByIdUser(newUser.getIdUser()));
		
		UserDAO userdao = new UserDAO();
		userdao.getAllUsers();
	}
	
	@Test
	public void TestGroup_CRUD(){
		
		GroupDAO groupDAO = new GroupDAO();
		Group newGroup = groupDAO.createGroup( new Group("CustomerAdmin"));
		
		groupDAO.createGroup(new Group("SysVentoryAdmin"));
		groupDAO.createGroup(new Group("Inventor"));
		groupDAO.createGroup(new Group("Visitor"));
		
		Group groupSelected = groupDAO.getGroupById(newGroup.getIdGroup());
		assertEquals(groupSelected.getIdGroup(), newGroup.getIdGroup());
		assertEquals(newGroup.getName(), groupSelected.getName());
		
		groupDAO.deleteGroup(newGroup);
		assertNull(groupDAO.getGroupById(newGroup.getIdGroup()));
	}
	
	@Test
	public void TestCompany_CRUD_AND_TestScanSetting(){
		CompanyDAO compDAO = new CompanyDAO();
		ScanSettingDAO scanSDAO = new ScanSettingDAO();
		
		Company comp = new Company("InnoSolv AG", "Ikarusstrasse", "9", null, "9015", "St. Gallen");	;
		ScanSetting scanSetting = new ScanSetting("DonRaul", "192.168.1.1", "192.168.1.35", 1, false);
		
		comp.setScanSetting(scanSetting);
		scanSetting.setCompany(comp);
		comp = compDAO.createCompany(comp);
		// Prüfen, ob 1-1 Beziehung wirklich vorhanden ist...
		assertEquals(comp.getIdCompany(), scanSDAO.getScanSettingById(comp.getIdCompany()).getIdCompany());
		
		// 2. Datensatz (ohne ScanSetting) einfügen...
		comp = new Company("Movento", "Irgendeinestrasse", "10", null, "9015", "St. Gallen");
		compDAO.createCompany(comp);
		assertNull(scanSDAO.getScanSettingById(comp.getIdCompany()));
		
		//.. und diese Company ohne ScanSetting gleich wieder löschen
		compDAO.deleteCompany(compDAO.getCompanyById(2));
		assertNull(compDAO.getCompanyById(2));
		
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
	public void TestScanJob(){
		ScanJob scanJ = new ScanJob("07:00", "07:30", new ScanStatus("Erledigt", "Irgendeine Beschreibung"));
		ScanJobDAO scanJobDAO = new ScanJobDAO();
		scanJobDAO.createScanJob(scanJ);
		
		ScanJob scanJobSelected = scanJobDAO.getScanJobById(1);
		assertEquals("07:00", scanJobSelected.getStartTime());
		assertEquals("07:30", scanJobSelected.getEndTime());
		//assertEquals("Erledigt", scanJobSelected.getStatus().getName());
		
		scanJobDAO.deleteScanJob(scanJobDAO.getScanJobById(1));
		assertNull(scanJobDAO.getScanJobById(1));
	}	
}
