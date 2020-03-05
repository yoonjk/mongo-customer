package com.ibm.lab;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ibm.lab.customer.domain.Customer;
import com.ibm.lab.customer.repository.CustomerRepository;

@SpringBootApplication
public class DemoCustomerApplication implements CommandLineRunner{
    private static Logger logger = LoggerFactory.getLogger(DemoCustomerApplication.class);

    @Autowired
    private CustomerRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(DemoCustomerApplication.class, args);
	}

    @Override
    public void run(String... strings) throws Exception {
        repository.deleteAll();

        // save a couple of customers
        repository.save(new Customer("001","Alice", "Smith"));
        repository.save(new Customer("002","Bob", "Smith"));
        repository.save(new Customer("003","Jaeguk", "Yun"));

        // fetch all customers
        logger.info("Customers found with findAll():");
        logger.info("-------------------------------");
        for (Customer customer : repository.findAll()) {
            logger.info(customer.toString());
        }
        logger.info("");

        // fetch and update an individual customer
        logger.info("Customer found with findByCode('001'):");
        logger.info("--------------------------------");
        Optional<Customer> theCustomer = repository.findById("001");
        logger.info(theCustomer.toString());
        theCustomer.get().setAge(12);
        repository.save(theCustomer.get());
        Optional<Customer> theCustomer2= repository.findById("001");
        logger.info("updated customer:"+theCustomer2.toString());
        logger.info("");

        logger.info("Customers found with findByLastName('Smith'):");
        logger.info("--------------------------------");
        for (Customer customer : repository.findByLastName("Smith")) {
            logger.info(customer.toString());
        }
    }
}
