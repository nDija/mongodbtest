package com.boulanger.mongotest.repository;

import com.boulanger.mongotest.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerRepositoryTests {

	@Autowired
	public CustomersRepository customerRepository;

	@Test
	void shouldBeSavedCustomer() {
		Customer customer = new Customer("Jo","Exotic", "jo.exotic@gmail.com");
		customerRepository.save(customer);
		Customer customerFromDB = customerRepository.findByeMail("jo.exotic@gmail.com");
		Assertions.assertEquals(customerFromDB.toString(), customer.toString());
	}
}
