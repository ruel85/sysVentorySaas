package ch.zbw.sysVentorySaas.App.DataAccessObject;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.NetworkInterface;
import ch.zbw.sysVentorySaas.App.model.OperatingSystem;
import ch.zbw.sysVentorySaas.App.model.Software;

public class OperatingSystemDAO {
	
	public static OperatingSystem saveOperatingSystem(OperatingSystem operatingSystem){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(operatingSystem);
		transaction.commit();
		return operatingSystem;
	}
	
	public static OperatingSystem getOperatingSystemById(int id){
		OperatingSystem newObject = new OperatingSystem();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		newObject = (OperatingSystem) session.get(OperatingSystem.class, id);
		transaction.commit();
		return newObject;
	}
	
	public static List<OperatingSystem> getOperatingSystemByIdDevice(int idDevice){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from OperatingSystem where idDevice = :idDevice");
		query.setParameter("idDevice", idDevice);
		List<OperatingSystem> operatingSystem = query.list();
		session.getTransaction().commit();
		return operatingSystem;	
	}
	
	public static OperatingSystem deleteOperatingSystem(OperatingSystem operatingSystem)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(operatingSystem);
		transaction.commit();
		return operatingSystem;
	}
}
