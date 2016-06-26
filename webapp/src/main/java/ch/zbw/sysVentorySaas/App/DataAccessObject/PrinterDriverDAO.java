package ch.zbw.sysVentorySaas.App.DataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.PrinterDriver;

public class PrinterDriverDAO {
	public static PrinterDriver createPrinterDriver(PrinterDriver printerDriver){
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
	
	public static PrinterDriver deletePrinterDriver(PrinterDriver printerDriver)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(printerDriver);
		transaction.commit();
		return printerDriver;
	}

}
