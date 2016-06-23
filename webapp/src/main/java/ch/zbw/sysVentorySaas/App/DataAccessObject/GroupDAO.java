package ch.zbw.sysVentorySaas.App.DataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.Group;

public class GroupDAO {
	public static Group createGroup(Group group){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(group);
		transaction.commit();
		return group;
	}
	
	public static Group getGroupById(int id){
		Group newObject = new Group();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		newObject = (Group) session.get(Group.class, id);
		transaction.commit();
		return newObject;
	}
	
	public static Group deleteGroup(Group group)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(group);
		transaction.commit();
		return group;
	}
}
