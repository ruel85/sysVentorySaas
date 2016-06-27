package ch.zbw.sysVentorySaas.App.DataAccessObject;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.Printer;
import ch.zbw.sysVentorySaas.App.model.PrinterDriver;

public class PrinterDriverDAO {
	public static PrinterDriver savePrinterDriver(PrinterDriver printerDriver){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(printerDriver);
		transaction.commit();
		return printerDriver;
	}
	
	public static PrinterDriver getPrinterDriverById(int id){
		PrinterDriver newObject = new PrinterDriver();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		newObject = (PrinterDriver) session.get(PrinterDriver.class, id);
		transaction.commit();
		return newObject;
	}
	
	public static List<PrinterDriver> getPrinterDriverByIdDevice(int idDevice){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from PrinterDriver where idDevice = :idDevice");
		query.setParameter("idDevice", idDevice);
		List<PrinterDriver> printerDriver = query.list();
		session.getTransaction().commit();
		return printerDriver;	
	}
	
	public static PrinterDriver deletePrinterDriver(PrinterDriver printerDriver)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(printerDriver);
		transaction.commit();
		return printerDriver;
	}

}
