package ch.zbw.sysVentorySaas.App.DataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.SID;

public class SIDDAO {

	public static SID createSID(SID sid){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(sid);
		transaction.commit();
		return sid;
	}
	
	public static SID getSIDById(int id){
		SID newObject = new SID();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		newObject = (SID) session.get(SID.class, id);
		transaction.commit();
		return newObject;
	}
	
	public static SID deleteCompany(SID sid)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(sid);
		transaction.commit();
		return sid;
	}
	
}
