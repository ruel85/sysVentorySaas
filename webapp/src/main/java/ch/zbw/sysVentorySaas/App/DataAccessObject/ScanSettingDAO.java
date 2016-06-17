package ch.zbw.sysVentorySaas.App.DataAccessObject;

import javax.persistence.JoinTable;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.ScanSetting;
import ch.zbw.sysVentorySaas.App.model.User;

public class ScanSettingDAO {

	public ScanSetting createScanSetting(ScanSetting scanSetting){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(scanSetting);
		transaction.commit();
		return scanSetting;
	}
	
	public ScanSetting getScanSettingById(int id){
		ScanSetting newObject = new ScanSetting();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		newObject = (ScanSetting) session.get(ScanSetting.class, id);
		transaction.commit();
		return newObject;
	}
	
	public void deleteScanSettings(ScanSetting scanSetting){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(scanSetting);
		transaction.commit();
	}

	public ScanSetting getScanSettingByUID(byte[] uID) {
		UserDAO uDAO = new UserDAO();
		User user = uDAO.getUserByUID(uID);		
		return new ScanSetting();
	}
}
