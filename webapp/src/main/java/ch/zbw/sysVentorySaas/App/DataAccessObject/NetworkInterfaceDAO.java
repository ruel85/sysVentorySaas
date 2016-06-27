package ch.zbw.sysVentorySaas.App.DataAccessObject;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.Device;
import ch.zbw.sysVentorySaas.App.model.NetworkInterface;
import ch.zbw.sysVentorySaas.App.model.OperatingSystem;

public class NetworkInterfaceDAO {
	
	public static NetworkInterface saveNetworkInterface(NetworkInterface networkInterface){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(networkInterface);
		transaction.commit();
		return networkInterface;
	}
	
	public static NetworkInterface getNetworkInterfaceById(int id){
		NetworkInterface newObject = new NetworkInterface();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		newObject = (NetworkInterface) session.get(NetworkInterface.class, id);
		transaction.commit();
		return newObject;
	}
	
	public static List<NetworkInterface> getNetworkInterfacesByDeviceId(int idDevice)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from NetworkInterface where idDevice = :idDevice");
		query.setParameter("idDevice", idDevice);
		List<NetworkInterface> interfaces = query.list();
		session.getTransaction().commit();
		return interfaces;	
	}
	
	public static NetworkInterface deleteNetworkInterface(NetworkInterface networkInterface)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(networkInterface);
		transaction.commit();
		return networkInterface;
	}
}
