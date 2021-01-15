package com.boulanger.mongotest.repository;

import com.boulanger.mongotest.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CustomerRepositoryTests {

    @Autowired
    public CustomerRepository customerRepository;

    private Customer joExotic = new Customer("Jo", "Exotic", "jo.exotic@gmail.com");
    private Customer caroleBaskin = new Customer("Carole", "Baskin", "Carole.Baskin@gmail.com");

    @Test
    void shouldBeSavedCustomer() {
        customerRepository.deleteAll();
        customerRepository.save(joExotic);
        Customer customerFromDB = customerRepository.findByeMail("jo.exotic@gmail.com");
        Assertions.assertEquals(customerFromDB.toString(), joExotic.toString());
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

    @Test
    public void shouldCustomereMailUnique() {
        customerRepository.deleteAll();
        customerRepository.save(joExotic);
        try {
            Customer fakeJo = new Customer("jo", "exotic", "jo.exotic@gmail.com");
            customerRepository.save(fakeJo);
        } catch (Exception e) {
            Assertions.assertTrue(true);
            return;
        }
        Assertions.assertTrue(false);
    }

    @Test
    public void shouldReturnTwoCustomer() {

        customerRepository.deleteAll();
        customerRepository.save(joExotic);
        customerRepository.save(caroleBaskin);

        List<Customer> customers = customerRepository.findAll();
        Assertions.assertEquals(2, customers.size());
    }

    @Test
    public void shouldDeleteCustomer() {
        customerRepository.deleteAll();
        customerRepository.save(joExotic);
        customerRepository.deleteByeMail("jo.exotic@gmail.com");
        Customer customerFromDb = customerRepository.findByeMail("jo.exotic@gmail.com");
        Assertions.assertNull(customerFromDb);
    }
}
