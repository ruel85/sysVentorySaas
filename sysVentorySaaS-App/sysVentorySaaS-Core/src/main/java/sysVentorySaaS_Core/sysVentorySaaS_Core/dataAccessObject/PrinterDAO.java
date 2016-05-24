package sysVentorySaaS_Core.sysVentorySaaS_Core.dataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import sysVentorySaaS_Core.sysVentorySaaS_Core.helpers.HibernateUtil;
import sysVentorySaaS_Core.sysVentorySaaS_Core.model.Printer;

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
