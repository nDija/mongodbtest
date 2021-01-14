package com.boulanger.mongotest.repository;

import com.boulanger.mongotest.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerRepositoryTests {

    @Autowired
    public CustomerRepository customerRepository;

    @Test
    void shouldBeSavedCustomer() {
        customerRepository.deleteAll();
        Customer customer = new Customer("Jo", "Exotic", "jo.exotic@gmail.com");
        customerRepository.save(customer);
        Customer customerFromDB = customerRepository.findByeMail("jo.exotic@gmail.com");
        Assertions.assertEquals(customerFromDB.toString(), customer.toString());
    }

    @Test
    void shouldCustomerFieldsNotNull() {
        Customer c;
        try {
            c = new Customer(null, null, null);
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
            return;
        }
        Assertions.assertTrue(false);
    }

}
