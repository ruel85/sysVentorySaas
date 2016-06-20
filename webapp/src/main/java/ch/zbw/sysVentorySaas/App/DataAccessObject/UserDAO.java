package ch.zbw.sysVentorySaas.App.DataAccessObject;

import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.runtime.directive.Parse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.Software;
import ch.zbw.sysVentorySaas.App.model.User;

public class UserDAO {
	
	public User createUser(User user){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(user);
		transaction.commit();
		return user;
	}
	
	public User deleteUser(User user)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(user);
		transaction.commit();
		return user;
	}

	public User getUserByUID(byte[] uID) {
		User newObject = new User();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		newObject = (User) session.get(User.class, uID);
		transaction.commit();
		return newObject;
	}
	
	public User getUserByIdUser(int idUser) {
		User sEmpty = new User();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		sEmpty = (User) session.get(User.class, idUser);
		transaction.commit();
		return sEmpty;
	}
	
	public List<User> getAllUsers()
	{
		List<User> users = new ArrayList<User>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		users = session.createQuery("FROM User").list(); 
		transaction.commit();
		return users;
	}

}
