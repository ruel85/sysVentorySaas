package sysVentorySaaS_Core.sysVentorySaaS_Core.dataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import sysVentorySaaS_Core.sysVentorySaaS_Core.helpers.HibernateUtil;
import sysVentorySaaS_Core.sysVentorySaaS_Core.model.Software;


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
		transaction.commit();
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
