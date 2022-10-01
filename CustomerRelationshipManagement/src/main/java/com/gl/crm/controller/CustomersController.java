package com.gl.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.crm.entity.Customer;
import com.gl.crm.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomersController {

	@Autowired
	private CustomerService customerService;

	// add mapping for "/list"
	@RequestMapping("/list")
	public String listCustomers(Model theModel) {
		// get Customers from dbase
		System.out.println("request recieved");
		List<Customer> theCustomer = customerService.findAll();
		theModel.addAttribute("Customers", theCustomer);
		return "list-Customers";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Customer theCustomer = new Customer();

		theModel.addAttribute("Customers", theCustomer);

		return "Customer-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		// get the Customer from the service
		Customer theCustomer = customerService.findById(theId);

		// set the Customer as a model to pre-populate the form
		theModel.addAttribute("Customers", theCustomer);

		return "Customer-form";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("customerId") int theId) {

		// delete the Customer
		customerService.deleteById(theId);

		// redirect to /Customers/list
		return "redirect:/customer/list";

	}

	@PostMapping("/save")
	public String saveCustomer(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email) {

		System.out.println(id);
		Customer theCustomer;
		if (id != 0) {
			theCustomer = customerService.findById(id);
			theCustomer.setFirstName(firstName);
			theCustomer.setLastName(lastName);
			theCustomer.setEmail(email);
		} else {
			theCustomer = new Customer(id, firstName, lastName, email);
			// save the Customer
			customerService.save(theCustomer);
		}
		// use a redirect to prevent duplicate submissions
		return "redirect:/customer/list";

	}

}
