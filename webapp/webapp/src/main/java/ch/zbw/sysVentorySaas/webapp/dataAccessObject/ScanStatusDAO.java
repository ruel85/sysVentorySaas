package ch.zbw.sysVentorySaas.webapp.dataAccessObject;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.webapp.HibernateUtil;
import ch.zbw.sysVentorySaas.webapp.model.ScanStatus;

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
