package sysVentorySaaS_Core.sysVentorySaaS_Core.dataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import sysVentorySaaS_Core.sysVentorySaaS_Core.helpers.HibernateUtil;
import sysVentorySaaS_Core.sysVentorySaaS_Core.model.OperatingSystem;

public class OperatingSystemDAO {
	public void createOperatingSystem(OperatingSystem operatingSystem){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(operatingSystem);
		transaction.commit();
	}
	
	public OperatingSystem getOperatingSystemById(int id){
		OperatingSystem sEmpty = new OperatingSystem();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		sEmpty = (OperatingSystem) session.get(OperatingSystem.class, id);
		transaction.commit();
		return sEmpty;
	}
	
	public void deleteOperatingSystem(OperatingSystem operatingSystem)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(operatingSystem);
		transaction.commit();
	}
}
