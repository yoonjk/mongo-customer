package com.ibm.lab.customer.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibm.lab.customer.domain.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    List<Customer> findByLastName(String lastName);
}
