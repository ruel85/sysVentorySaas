package ch.zbw.sysVentorySaas.App.DataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.Company;

public class CompanyDAO {
	
	public Company createCompany(Company company){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(company);
		transaction.commit();
		return company;
	}
	
	public Company getCompanyById(int id){
		Company newObject = new Company();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		newObject = (Company) session.get(Company.class, id);
		transaction.commit();
		return newObject;
	}
	
	public Company deleteCompany(Company company)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(company);
		transaction.commit();
		return company;
	}
}