package com.ibm.lab;

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
        repository.save(new Customer(1l,"001","Alice", "Smith"));
        repository.save(new Customer(2l,"002","Bob", "Smith"));
        repository.save(new Customer(3l,"003","Jaeguk", "Yun"));

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
        Customer theCustomer = repository.findByCode("001");
        logger.info(theCustomer.toString());
        theCustomer.setAge(12);
        repository.save(theCustomer);
        Customer theCustomer2 = repository.findByCode("001");
        logger.info("updated customer:"+theCustomer2.toString());
        logger.info("");

        logger.info("Customers found with findByLastName('Smith'):");
        logger.info("--------------------------------");
        for (Customer customer : repository.findByLastName("Smith")) {
            logger.info(customer.toString());
        }
    }
}
