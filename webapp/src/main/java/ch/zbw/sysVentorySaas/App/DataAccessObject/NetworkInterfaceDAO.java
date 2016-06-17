package ch.zbw.sysVentorySaas.App.DataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.NetworkInterface;
import ch.zbw.sysVentorySaas.App.model.OperatingSystem;

public class NetworkInterfaceDAO {
	public NetworkInterface createNetworkInterface(NetworkInterface networkInterface){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(networkInterface);
		transaction.commit();
		return networkInterface;
	}
	
	public NetworkInterface getNetworkInterfaceById(int id){
		NetworkInterface newObject = new NetworkInterface();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		newObject = (NetworkInterface) session.get(NetworkInterface.class, id);
		transaction.commit();
		return newObject;
	}
	
	public NetworkInterface deleteNetworkInterface(NetworkInterface networkInterface)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(networkInterface);
		transaction.commit();
		return networkInterface;
	}
}
