package sysVentorySaaS_Core.sysVentorySaaS_Core.dataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import sysVentorySaaS_Core.sysVentorySaaS_Core.helpers.HibernateUtil;
import sysVentorySaaS_Core.sysVentorySaaS_Core.model.ScanJob;

public class ScanJobDAO {

	public void createScanJob(ScanJob scanJob){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(scanJob);
		transaction.commit();
	}
	
	public ScanJob getScanJobById(int id){
		ScanJob sEmpty = new ScanJob();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		sEmpty = (ScanJob) session.get(ScanJob.class, id);
		transaction.commit();
		return sEmpty;
	}
	
	public void deleteScanJob(ScanJob scanJob)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(scanJob);
		transaction.commit();
	}
}