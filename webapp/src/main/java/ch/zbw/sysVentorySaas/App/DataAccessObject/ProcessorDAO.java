package ch.zbw.sysVentorySaas.App.DataAccessObject;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.PrinterDriver;
import ch.zbw.sysVentorySaas.App.model.Processor;

public class ProcessorDAO {
	
	public static Processor saveProcessor(Processor processor){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(processor);
		transaction.commit();
		return processor;
	}
	
	public static Processor getProcessorById(int id){
		Processor newObject = new Processor();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		newObject = (Processor) session.get(Processor.class, id);
		transaction.commit();
		return newObject;
	}
	
	public static List<Processor> getProcessorByIdDevice(int idDevice){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Processor where idDevice = :idDevice");
		query.setParameter("idDevice", idDevice);
		List<Processor> processor = query.list();
		session.getTransaction().commit();
		return processor;	
	}
	
	public static void deleteProcessor(Processor processor)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(processor);
		transaction.commit();
	}
}
