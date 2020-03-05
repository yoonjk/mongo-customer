package com.ibm.lab.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.lab.customer.domain.Customer;
import com.ibm.lab.customer.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	
	public List<Customer> findAll() {
		
		return customerRepository.findAll();
	}
}
