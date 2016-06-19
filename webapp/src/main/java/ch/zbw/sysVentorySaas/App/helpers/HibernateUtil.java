package ch.zbw.sysVentorySaas.App.helpers;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory;
	
	static {
			Configuration conf = new Configuration().configure("hibernate.cfg.xml");
			sessionFactory = conf.buildSessionFactory();
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}
