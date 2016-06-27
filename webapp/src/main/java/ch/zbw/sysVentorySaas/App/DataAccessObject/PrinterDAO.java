package ch.zbw.sysVentorySaas.App.DataAccessObject;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.OperatingSystem;
import ch.zbw.sysVentorySaas.App.model.Printer;

public class PrinterDAO {
	
	public static Printer savePrinter(Printer printer){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(printer);
		transaction.commit();
		return printer;
	}
	
	public static Printer getPrinterById(int id){
		Printer newObject = new Printer();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		newObject = (Printer) session.get(Printer.class, id);
		transaction.commit();
		return newObject;
	}
	
	public static List<Printer> getPrinterByIdDevice(int idDevice){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Printer where idDevice = :idDevice");
		query.setParameter("idDevice", idDevice);
		List<Printer> printer = query.list();
		session.getTransaction().commit();
		return printer;	
	}
	
	public static Printer deleteProcessor(Printer printer)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(printer);
		transaction.commit();
		return printer;
	}
}
