package ch.zbw.sysVentorySaas.App.DataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.Processor;

public class ProcessorDAO {
	public void createProcessor(Processor processor){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(processor);
		transaction.commit();
	}
	
	public Processor getProcessorById(int id){
		Processor sEmpty = new Processor();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		sEmpty = (Processor) session.get(Processor.class, id);
		transaction.commit();
		return sEmpty;
	}
	
	public void deleteProcessor(Processor processor)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(processor);
		transaction.commit();
	}
}
