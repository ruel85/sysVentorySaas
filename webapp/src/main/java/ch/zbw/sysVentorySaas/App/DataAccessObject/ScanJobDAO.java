package ch.zbw.sysVentorySaas.App.DataAccessObject;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.ScanJob;
import ch.zbw.sysVentorySaas.App.model.User;

public class ScanJobDAO {

	public static ScanJob saveScanJob(ScanJob scanJob){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(scanJob);
		transaction.commit();
		return scanJob;
	}
	
	public static ScanJob getScanJobById(int id){
		ScanJob newObject = new ScanJob();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		newObject = (ScanJob) session.get(ScanJob.class, id);
		transaction.commit();
		return newObject;
	}
	
	public static ScanJob deleteScanJob(ScanJob scanJob)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(scanJob);
		transaction.commit();
		return scanJob;
	}
	
	public static List<ScanJob> getAllScanJobs(int idCompany)
	{
		List<ScanJob> scanjobs = new ArrayList<ScanJob>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("FROM ScanJob WHERE idCompany = :idCompany"); 
		query.setParameter("idCompany", idCompany);
		scanjobs = query.list();
		transaction.commit();
		return scanjobs;
	}
}
