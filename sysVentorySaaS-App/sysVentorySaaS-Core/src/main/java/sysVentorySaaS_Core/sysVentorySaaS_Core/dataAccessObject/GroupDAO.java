package sysVentorySaaS_Core.sysVentorySaaS_Core.dataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import sysVentorySaaS_Core.sysVentorySaaS_Core.helpers.HibernateUtil;
import sysVentorySaaS_Core.sysVentorySaaS_Core.model.Group;

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
