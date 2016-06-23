package ch.zbw.sysVentorySaas.App.DataAccessObject;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.Company;
import ch.zbw.sysVentorySaas.App.model.ScanSetting;
import ch.zbw.sysVentorySaas.App.model.User;

public class OtherDAO {
	
	private static ScanSetting scanSetting;

	public static final ScanSetting getScanSettingByUID(String uID)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("FROM User where uID = :uID");
		query.setParameter("uID", uID);
		User user = (User) query.list().get(0);
		
		query = session.createQuery("FROM Company where idCompany = :idCompany");
		Hibernate.initialize(user.getCompany());
		Hibernate.initialize(user.getCompany().getIdCompany());
		query.setParameter("idCompany", user.getCompany().getIdCompany());
		Company comp = (Company) query.list().get(0);
		
		if(comp != null && comp.getIdCompany() != 0)
		{
			query = session.createQuery("FROM ScanSetting where idScanSetting = :idScanSetting");
			Hibernate.initialize(comp);
			Hibernate.initialize(comp.getIdCompany());
			query.setParameter("idScanSetting", comp.getIdCompany());
			scanSetting = (ScanSetting) query.list().get(0);	
		}
		
		transaction.commit();
		return scanSetting;
		
	}
	
}
