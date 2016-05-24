package sysVentorySaaS_Core.sysVentorySaaS_Core.dataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import sysVentorySaaS_Core.sysVentorySaaS_Core.helpers.HibernateUtil;
import sysVentorySaaS_Core.sysVentorySaaS_Core.model.User;

public class UserDAO {
	public void createUser(User user){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(user);
		transaction.commit();
	}
	
	public User getUserbyId(int id){
		User sEmpty = new User();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		sEmpty = (User) session.get(User.class, id);
		transaction.commit();
		return sEmpty;
	}
	
	public void deleteUser(User user)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(user);
		transaction.commit();
	}
}
