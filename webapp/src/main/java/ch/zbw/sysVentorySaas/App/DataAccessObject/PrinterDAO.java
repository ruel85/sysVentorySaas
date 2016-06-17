package ch.zbw.sysVentorySaas.App.DataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.Printer;

public class PrinterDAO {
	
	public Printer createPrinter(Printer printer){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(printer);
		transaction.commit();
		return printer;
	}
	
	public Printer getPrinterById(int id){
		Printer newObject = new Printer();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		newObject = (Printer) session.get(Printer.class, id);
		transaction.commit();
		return newObject;
	}
	
	public Printer deleteProcessor(Printer printer)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(printer);
		transaction.commit();
		return printer;
	}
}
