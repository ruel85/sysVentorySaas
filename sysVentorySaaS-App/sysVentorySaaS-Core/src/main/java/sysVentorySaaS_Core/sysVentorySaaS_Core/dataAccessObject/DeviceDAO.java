package sysVentorySaaS_Core.sysVentorySaaS_Core.dataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import sysVentorySaaS_Core.sysVentorySaaS_Core.helpers.HibernateUtil;
import sysVentorySaaS_Core.sysVentorySaaS_Core.model.Device;

public class DeviceDAO {
	public void createDevice(Device device){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(device);
		transaction.commit();
	}
	
	public Device getDeviceById(int id){
		Device sEmpty = new Device();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		sEmpty = (Device) session.get(Device.class, id);
		transaction.commit();
		return sEmpty;
	}
	
	public void deleteDevice(Device device)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(device);
		transaction.commit();
	}
}
