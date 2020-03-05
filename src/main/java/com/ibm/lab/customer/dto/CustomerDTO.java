package com.ibm.lab.customer.dto;

import java.io.Serializable;

import com.ibm.lab.customer.domain.Customer;

public class CustomerDTO implements Serializable {
	private String id;
    private String firstName;
    private String lastName;
    private int age;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public CustomerDTO() {
		
	}
	public CustomerDTO(Customer c) {
		id = c.getId();
		firstName = c.getFirstName();
		lastName = c.getLastName();
		age = c.getAge();
	}
}
