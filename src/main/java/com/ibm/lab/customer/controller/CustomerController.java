package com.ibm.lab.customer.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.lab.customer.domain.Customer;
import com.ibm.lab.customer.dto.CustomerDTO;
import com.ibm.lab.customer.service.CustomerService;

@RestController
@RequestMapping(value="/customers")
public class CustomerController {
	@Autowired
	CustomerService customer;
	
	@GetMapping("")
	public ResponseEntity<List<CustomerDTO>> findAll() {
		List<Customer> customerList = customer.findAll();
		List<CustomerDTO> listDto = customerList.stream().map(x -> new CustomerDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}
