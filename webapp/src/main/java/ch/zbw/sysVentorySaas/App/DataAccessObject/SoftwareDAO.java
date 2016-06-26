package ch.zbw.sysVentorySaas.App.DataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.Software;

public class SoftwareDAO {

	public static Software saveSoftware(Software software){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(software);
		transaction.commit();
		return software;
	}
	
	public static Software getSoftwarebyId(int id){
		Software newObject = new Software();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		newObject = (Software) session.get(Software.class, id);
		transaction.commit();
		return newObject;
	}
	
	public static Software deleteSoftware(Software software)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(software);
		transaction.commit();
		return software;
	}
}
