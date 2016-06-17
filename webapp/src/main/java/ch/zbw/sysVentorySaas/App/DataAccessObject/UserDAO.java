package ch.zbw.sysVentorySaas.App.DataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.Software;
import ch.zbw.sysVentorySaas.App.model.User;

public class UserDAO {
	public void createUser(User user){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(user);
		transaction.commit();
	}
	
	public void deleteUser(User user)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(user);
		transaction.commit();
	}

	public User getUserByUID(byte[] uID) {
		User sEmpty = new User();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		sEmpty = (User) session.get(User.class, uID);
		transaction.commit();
		return sEmpty;
	}
	
	public User getUserByIdUser(int idUser) {
		User sEmpty = new User();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		sEmpty = (User) session.get(User.class, idUser);
		transaction.commit();
		return sEmpty;
	}
}
