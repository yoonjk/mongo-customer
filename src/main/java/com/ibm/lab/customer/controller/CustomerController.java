package com.ibm.lab.customer.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDTO> findById(@PathVariable String id) {
		Customer cust = customer.findById(id);
		
		return ResponseEntity.ok().body(new CustomerDTO(cust));
	}
	
	@PostMapping("") 
	public ResponseEntity <Object> insert(@RequestBody CustomerDTO custDTO) {
		Customer cust = customer.fromDTO(custDTO);
		cust = customer.insert(cust);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cust.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Object> delete(@PathVariable String id) {
		customer.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Object> update(@RequestBody CustomerDTO custDto, @PathVariable String id) {
		Customer cust = customer.fromDTO(custDto);
		cust.setId(id);
		cust = customer.update(cust);
		return ResponseEntity.noContent().build();
	}
}
