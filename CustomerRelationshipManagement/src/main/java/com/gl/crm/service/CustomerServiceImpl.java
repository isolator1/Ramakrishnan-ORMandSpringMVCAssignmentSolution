package com.gl.crm.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gl.crm.entity.Customer;

@Repository
public class CustomerServiceImpl implements CustomerService {
	private SessionFactory sessionFactory;

	// create session
	private Session session;

	@Autowired
	CustomerServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
	}

	@Transactional
	public List<Customer> findAll() {

		Transaction tx = session.beginTransaction();
		// find all th records from database table
		List<Customer> customer = session.createQuery("from Customer").list();
		tx.commit();
		return customer;
	}

	@Override
	public Customer findById(int theId) {
		Customer customer = new Customer();

		Transaction tx = session.beginTransaction();

		// find record with Id from the db

		customer = session.get(Customer.class, theId);
		tx.commit();

		return customer;
	}

	@Transactional
	public void save(Customer theCustomers) {
		Transaction tx = session.beginTransaction();
		// save transaction
		session.saveOrUpdate(theCustomers);
		tx.commit();

	}

	@Transactional
	public void deleteById(int id) {
		Transaction tx = session.beginTransaction();
		// get transaction
		Customer customer = session.get(Customer.class, id);

		// delete record

		session.delete(customer);
		tx.commit();

	}

}
