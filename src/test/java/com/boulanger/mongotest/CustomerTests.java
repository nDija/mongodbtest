package com.boulanger.mongotest;

import com.boulanger.mongotest.model.Customer;
import com.boulanger.mongotest.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerTests {

	@Autowired
	public CustomerRepository customerRepository;

	@Test
	void customerTest() {
		Customer customer = new Customer("1","Jo","Exotic");
		customerRepository.save(customer);
		Customer customerFromDB = customerRepository.findByFirstName("Jo");
		Assertions.assertEquals(customerFromDB.toString(), customer.toString());
	}
}
