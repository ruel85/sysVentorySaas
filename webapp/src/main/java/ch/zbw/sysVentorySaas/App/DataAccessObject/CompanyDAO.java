package ch.zbw.sysVentorySaas.App.DataAccessObject;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.zbw.sysVentorySaas.App.helpers.HibernateUtil;
import ch.zbw.sysVentorySaas.App.model.Company;
import ch.zbw.sysVentorySaas.App.model.ScanSetting;

public class CompanyDAO {
	
	public static Company createCompany(Company company){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(company);
		transaction.commit();
		return company;
	}
	
	public static Company getCompanyById(int id){
		Company newObject = new Company();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		newObject = (Company) session.get(Company.class, id);
		transaction.commit();
		return newObject;
	}
	
	public static Company deleteCompany(Company company)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(company);
		transaction.commit();
		return company;
	}
	
	public static List<Company> getAllCompanies() {
		List<Company> companies = new ArrayList<Company>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		companies = session.createQuery("FROM Company").list();
		transaction.commit();
		return companies;
	}
}