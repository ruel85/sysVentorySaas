package ch.zbw.sysVentorySaas.App.DataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.NetworkInterface;
import ch.zbw.sysVentorySaas.App.model.OperatingSystem;

public class NetworkInterfaceDAO {
	public void createNetworkInterface(NetworkInterface networkInterface){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(networkInterface);
		transaction.commit();
	}
	
	public NetworkInterface getNetworkInterfaceById(int id){
		NetworkInterface sEmpty = new NetworkInterface();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		sEmpty = (NetworkInterface) session.get(NetworkInterface.class, id);
		transaction.commit();
		return sEmpty;
	}
	
	public void deleteNetworkInterface(NetworkInterface networkInterface)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(networkInterface);
		transaction.commit();
	}
}