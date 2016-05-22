package ch.zbw.sysVentorySaas.App2.DataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App2.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App2.model.Software;
import ch.zbw.sysVentorySaas.App2.model.User;

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
