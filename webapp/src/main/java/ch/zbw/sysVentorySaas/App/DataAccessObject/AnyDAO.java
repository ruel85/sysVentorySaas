package ch.zbw.sysVentorySaas.App.DataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.Company;

public class AnyDAO<T> {
	final AnyDAO<T> typeParameterClass;
	
	public AnyDAO(AnyDAO<T> typeParameterClass){
	this.typeParameterClass = typeParameterClass;	
	}
	
	public AnyDAO<T> saveAnyEntity(AnyDAO<T> dao){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(dao);
		transaction.commit();
		return dao;
	}
	
	public AnyDAO<T> getAnyEntityById(int id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (AnyDAO<T>) session.get(typeParameterClass.getClass(), id);
	}
	
	public AnyDAO<T> deleteCompany(AnyDAO<T> anyDao)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(anyDao);
		transaction.commit();
		return anyDao;
	}
}