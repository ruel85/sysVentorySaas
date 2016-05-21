package ch.zbw.sysVentorySaas.App2.DataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App2.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App2.model.Software;

public class SoftwareDAO {

	public void createSoftware(Software software){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(software);
		transaction.commit();
	}
	
	public Software getSoftwarebyId(int id){
		Software sEmpty = new Software();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		sEmpty = (Software) session.get(Software.class, id);
		return sEmpty;
	}
	
	public void deleteSoftware(Software software)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(software);
		transaction.commit();
	}
	
}
