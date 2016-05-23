package ch.zbw.sysVentorySaas.App2.DataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App2.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App2.model.Service;

public class ServiceDAO {
	public void createService(Service service){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(service);
		transaction.commit();
	}
	
	public Service getServiceById(int id){
		Service sEmpty = new Service();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		sEmpty = (Service) session.get(Service.class, id);
		transaction.commit();
		return sEmpty;
	}
	
	public void deleteCompany(Service service)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(service);
		transaction.commit();
	}
}
