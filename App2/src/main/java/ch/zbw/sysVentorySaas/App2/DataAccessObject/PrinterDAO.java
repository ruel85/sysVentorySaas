package ch.zbw.sysVentorySaas.App2.DataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App2.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App2.model.Printer;

public class PrinterDAO {
	public void createPrinter(Printer printer){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(printer);
		transaction.commit();
	}
	
	public Printer getPrinterById(int id){
		Printer sEmpty = new Printer();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		sEmpty = (Printer) session.get(Printer.class, id);
		transaction.commit();
		return sEmpty;
	}
	
	public void deleteProcessor(Printer printer)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(printer);
		transaction.commit();
	}
}
