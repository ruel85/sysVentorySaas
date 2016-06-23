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

	@Fetch(FetchMode.JOIN)
	public User getUserByUID(String UID) {
		User newObject = new User();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("FROM User where uID = :uID");
		query.setParameter("uID", UID);
		List<User> users = query.list();	
		if(users.size() > 0)
		{
			for(User oneUser : users)
			{
				Hibernate.initialize(oneUser.getCompany());
				Hibernate.initialize(oneUser.getCompany().getIdCompany());
				
			}
		}
		transaction.commit();
		return (users.size()> 0) ? users.get(0) : newObject;
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
