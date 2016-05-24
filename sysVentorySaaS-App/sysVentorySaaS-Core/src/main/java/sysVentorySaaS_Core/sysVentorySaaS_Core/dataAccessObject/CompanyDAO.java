package sysVentorySaaS_Core.sysVentorySaaS_Core.dataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import sysVentorySaaS_Core.sysVentorySaaS_Core.helpers.HibernateUtil;
import sysVentorySaaS_Core.sysVentorySaaS_Core.model.Company;

public class CompanyDAO {
	
	public void createCompany(Company company){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(company);
		transaction.commit();
	}
	
	public Company getCompanyById(int id){
		Company sEmpty = new Company();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		sEmpty = (Company) session.get(Company.class, id);
		transaction.commit();
		return sEmpty;
	}
	
	public void deleteCompany(Company company)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(company);
		transaction.commit();
	}
}