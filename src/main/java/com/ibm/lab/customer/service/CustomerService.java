package com.ibm.lab.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.lab.customer.domain.Customer;
import com.ibm.lab.customer.dto.CustomerDTO;
import com.ibm.lab.customer.repository.CustomerRepository;
import com.ibm.lab.exception.ObjectNotFoundException;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	SequenceGeneratorService sequeneceGenerator;
	
	public List<Customer> findAll() {
		
		return customerRepository.findAll();
	}
	
	public Customer findById(String id) {
		Optional<Customer> cust = customerRepository.findById(id);
		return cust.orElseThrow(() -> new ObjectNotFoundException("Objeto not found"));
	}
	
	public Customer insert(Customer cust) {
		return customerRepository.insert(cust);
	}
	
	public Customer fromDTO(CustomerDTO custDto) {

		return new Customer(custDto.getId(), custDto.getFirstName(), custDto.getLastName(), custDto.getAge());
	}
	
	public void delete(String id) {
		findById(id);
		customerRepository.deleteById(id);
	}
	
	public Customer update(Customer cust) {
		Optional<Customer> existCust = customerRepository.findById(cust.getId());
		
		return customerRepository.save(cust);
	}
}
