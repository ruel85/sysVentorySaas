package sysVentorySaaS_Core.sysVentorySaaS_Core.dataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import sysVentorySaaS_Core.sysVentorySaaS_Core.helpers.HibernateUtil;
import sysVentorySaaS_Core.sysVentorySaaS_Core.model.ScanStatus;

public class ScanStatusDAO {

	public void createScanStatus(ScanStatus scanStatus){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(scanStatus);
		transaction.commit();
	}
	
	public ScanStatus getScanStatusById(int id){
		ScanStatus sEmpty = new ScanStatus();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		sEmpty = (ScanStatus) session.get(ScanStatus.class, id);
		transaction.commit();
		return sEmpty;
	}
	
	public void deleteScanStatus(ScanStatus scanStatus)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(scanStatus);
		transaction.commit();
	}
}
