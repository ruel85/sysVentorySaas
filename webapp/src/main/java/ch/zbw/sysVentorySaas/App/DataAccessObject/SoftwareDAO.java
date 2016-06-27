package ch.zbw.sysVentorySaas.App.DataAccessObject;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.SID;
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
	
	public static List<Software> getSoftwareByIdDevice(int idDevice){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Software where idDevice = :idDevice");
		query.setParameter("idDevice", idDevice);
		List<Software> software = query.list();
		session.getTransaction().commit();
		return software;	
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
