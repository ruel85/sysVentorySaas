package ch.zbw.sysVentorySaas.App.DataAccessObject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FetchType;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.User;

public class UserDAO {
	
	// Create or Update User
	public static User saveUser(User user){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(user);
		transaction.commit();
		return user;
	}
	
	// remove User
	public static User deleteUser(User user)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(user);
		transaction.commit();
		return user;
	}

	// get User by UID
	public static User getUserByUID(String UID) throws Exception {
		User user = new User();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("FROM User where uID = :uID");
		query.setParameter("uID", UID);
		List<User> users = query.list();	
		transaction.commit();
		
		if(users.size()== 0)
			throw new Exception("User mit der UID (" + UID + ") nicht gefunden!");
		user = users.get(0);
		return user;
	}
	
    // get User by ID
	public static User getUserByIdUser(int idUser) {
		User sEmpty = new User();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		sEmpty = (User) session.get(User.class, idUser);
		transaction.commit();
		return sEmpty;
	}
	
	// returns a List of Users
	public static List<User> getAllUsers()
	{
		List<User> users = new ArrayList<User>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		users = session.createQuery("FROM User").list();
		transaction.commit();
		return users;
	}
}
