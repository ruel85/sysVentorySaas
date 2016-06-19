package ch.zbw.sysVentorySaas.App.DataAccessObject;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.Device;

public class DeviceDAO {
	public Device createDevice(Device device){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(device);
		transaction.commit();
		return device;
	}
	
	public Device getDeviceById(int id){
		Device newObject = new Device();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		newObject = (Device) session.get(Device.class, id);
		transaction.commit();
		return newObject;
	}
	
	public Device deleteDevice(Device device){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(device);
		transaction.commit();
		return device;
	}
	
	public List<Device> getDevicesByScanJob(int idScanJob)
	{
		List<Device> devices = new ArrayList<Device>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List result = session.createQuery( "from Device" ).list();
		for (Device device : (List<Device>) result ) {
			devices.add(device);
		}
		session.getTransaction().commit();
		return devices;		
	}
}