package ch.zbw.sysVentorySaas.webapp.dataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.webapp.HibernateUtil;
import ch.zbw.sysVentorySaas.webapp.model.Group;

public class GroupDAO {
	public void createGroup(Group group){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(group);
		transaction.commit();
	}
	
	public Group getGroupById(int id){
		Group sEmpty = new Group();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		sEmpty = (Group) session.get(Group.class, id);
		transaction.commit();
		return sEmpty;
	}
	
	public void deleteGroup(Group group)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(group);
		transaction.commit();
	}
}
