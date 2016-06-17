package ch.zbw.sysVentorySaas.App.DataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.Service;

public class ServiceDAO {
	
	public Service createService(Service service){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(service);
		transaction.commit();
		return service;
	}
	
	public Service getServiceById(int id){
		Service newObject = new Service();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		newObject = (Service) session.get(Service.class, id);
		transaction.commit();
		return newObject;
	}
	
	public Service deleteCompany(Service service)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(service);
		transaction.commit();
		return service;
	}
}
