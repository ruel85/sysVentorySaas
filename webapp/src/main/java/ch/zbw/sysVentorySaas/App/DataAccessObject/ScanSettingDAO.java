package ch.zbw.sysVentorySaas.App.DataAccessObject;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.ScanSetting;

public class ScanSettingDAO {

	public static ScanSetting saveScanSetting(ScanSetting scanSetting) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(scanSetting);
		transaction.commit();
		return scanSetting;
	}

	public static ScanSetting getScanSettingById(int idCompany) throws Exception {
		ScanSetting newObject = new ScanSetting();
		List<ScanSetting> listScanSettings= getAllScanSettings();
		for(ScanSetting result : listScanSettings)
		{
			if(result.getIdCompany() == idCompany)
			{
				newObject = result;
				return newObject;
			}
		}
		throw new Exception("ScanSetting konnte aufgrund der ID (" + idCompany +") nicht ermittelt werden. Setting bitte anlegen, wenn keine vorhanden sein sollte!");
	}

	public static void deleteScanSettings(ScanSetting scanSetting) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(scanSetting);
		transaction.commit();
	}

	public static List<ScanSetting> getAllScanSettings() {
		List<ScanSetting> scanSettings = new ArrayList<ScanSetting>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		scanSettings = session.createQuery("FROM ScanSetting").list();
		transaction.commit();
		return scanSettings;
	}

	/*
	 * public void setTimeToScan(String uID, boolean b) { Session session =
	 * HibernateUtil.getSessionFactory().getCurrentSession(); Transaction
	 * transaction = session.beginTransaction();
	 * 
	 * User user = new UserDAO().getUserByUID(uID);
	 * 
	 * 
	 * if(user != null) { int idCompany = user.getCompany().getIdCompany();
	 * ScanSetting sc = session.createQuery("FROM ScanSetting where  ")
	 * 
	 * } session.delete(scanSetting); transaction.commit();
	 * 
	 * }
	 */
}