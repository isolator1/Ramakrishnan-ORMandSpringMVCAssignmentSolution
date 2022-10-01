package com.gl.crm.service;

import java.util.List;

import com.gl.crm.entity.Customer;

public interface CustomerService {

	public List<Customer> findAll();

	public Customer findById(int theId);

	public void save(Customer theCustomers);

	public void deleteById(int theId);

}
