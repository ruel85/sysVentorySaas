package ch.zbw.sysVentorySaas.App.DataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.ScanStatus;

public class ScanStatusDAO {

	public static ScanStatus saveScanStatus(ScanStatus scanStatus){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(scanStatus);
		transaction.commit();
		return scanStatus;
	}
	
	public static ScanStatus getScanStatusById(int id){
		ScanStatus newObject = new ScanStatus();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		newObject = (ScanStatus) session.get(ScanStatus.class, id);
		transaction.commit();
		return newObject;
	}
	
	public static ScanStatus deleteScanStatus(ScanStatus scanStatus)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(scanStatus);
		transaction.commit();
		return scanStatus;
	}
}
