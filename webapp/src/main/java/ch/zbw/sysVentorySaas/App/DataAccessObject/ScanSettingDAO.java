package ch.zbw.sysVentorySaas.App.DataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.ScanSetting;

public class ScanSettingDAO {

	public void createScanSetting(ScanSetting scanSetting){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(scanSetting);
		transaction.commit();
	}
	
	public ScanSetting getScanSettingById(int id){
		ScanSetting sEmpty = new ScanSetting();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		sEmpty = (ScanSetting) session.get(ScanSetting.class, id);
		transaction.commit();
		return sEmpty;
	}
	
	public void deleteScanSettings(ScanSetting scanSetting)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(scanSetting);
		transaction.commit();
	}
}
