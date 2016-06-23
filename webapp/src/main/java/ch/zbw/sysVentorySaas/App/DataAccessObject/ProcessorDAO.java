package ch.zbw.sysVentorySaas.App.DataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.Processor;

public class ProcessorDAO {
	
	public static void createProcessor(Processor processor){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(processor);
		transaction.commit();
	}
	
	public static Processor getProcessorById(int id){
		Processor newObject = new Processor();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		newObject = (Processor) session.get(Processor.class, id);
		transaction.commit();
		return newObject;
	}
	
	public static void deleteProcessor(Processor processor)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(processor);
		transaction.commit();
	}
}
