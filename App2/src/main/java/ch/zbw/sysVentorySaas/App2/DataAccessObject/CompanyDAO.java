package ch.zbw.sysVentorySaas.App2.DataAccessObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App2.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App2.model.Company;

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