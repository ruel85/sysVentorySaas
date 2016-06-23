package ch.zbw.sysVentorySaas.App.DataAccessObject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinTable;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.Company;
import ch.zbw.sysVentorySaas.App.model.NetworkInterface;
import ch.zbw.sysVentorySaas.App.model.ScanSetting;
import ch.zbw.sysVentorySaas.App.model.User;

public class ScanSettingDAO {

	public static ScanSetting createScanSetting(ScanSetting scanSetting) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(scanSetting);
		transaction.commit();
		return scanSetting;
	}

	public static ScanSetting getScanSettingById(int id) {
		ScanSetting newObject = new ScanSetting();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		newObject = (ScanSetting) session.get(ScanSetting.class, id);
		transaction.commit();
		return newObject;
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