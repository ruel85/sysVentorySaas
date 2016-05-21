package ch.zbw.sysVentorySaas.App2;

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

import ch.zbw.sysVentorySaas.App2.model.Software;

public class HibernateTest {

	
	private String user;
	private String password;
	private String url;
	private String mySQLParams;
	
	private Connection con;
	private String st;
	private SessionFactory sessionFactory;
	
	
	@Before
	public void setUp(){
		
		user ="SysVentoryAdmin";
		password="vdjjmf#n$ri7cr!?+RX7ZVbY5";
		url="jdbc:mysql://ruelholderegger.ch:3306/SysVentorySaas";
		mySQLParams = "?useSSL=false&serverTimezone=Europe/Paris";
	}
	
	@Test
	public void testDB_MySQL(){
			try {
				con = DriverManager.getConnection(url + mySQLParams, user, password);
				st = "Show databases;";
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(st);
				rs.next();
				System.out.println(rs.getString(1));
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	@Test
	public void TestInsertSoftware()
	{
		Software software = new Software();
		software.setId_software(1);
		software.setName("Windows");
		software.setVersion("10");
		software.setManufacturer("Microsoft Windows");

		Configuration conf2 = new Configuration().configure("hibernate.cfg.xml");
		sessionFactory = conf2.buildSessionFactory();		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(software);
		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	public void TestSelectSoftware()
	{
		Configuration conf2 = new Configuration().configure("hibernate.cfg.xml");
		sessionFactory = conf2.buildSessionFactory();		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Software sw = session.get(Software.class, 1);
		session.getTransaction().commit();
		System.out.println(sw.getId_software());
		session.close();
	}

}
