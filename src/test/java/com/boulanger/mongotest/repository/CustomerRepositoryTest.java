package com.boulanger.mongotest.repository;

import com.boulanger.mongotest.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest

public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void shouldCreateCustomer() throws Exception {
        customerRepository.deleteAll();
        Customer customer = new Customer("Eva", "Chochoi", "eva.chochoi@gmail.com");
        customerRepository.save(customer);  //enregistrer customer en base
        Customer customerFromDb = customerRepository.findByeMail("eva.chochoi@gmail.com"); // on le recherche avec l'email
        Assertions.assertEquals(customerFromDb.toString(), customer.toString()); //compare les objets avec les chaine de caractères en sortie
    }

    @Test
    public void shouldCustomerFieldsNotNull() {
        Customer customer;
        try {
            customer = new Customer(null, null, null);
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
            return;
        }
        Assertions.assertTrue(false);
    }

    @Test
    public void shouldCustomereMailUnique() {
        Customer customer1 = new Customer("Eva", "Chochoi", "eva.chochoi@gmail.com");
        Customer customer2 = new Customer("Eva", "Chochoi", "eva.chochoi@gmail.com");

        customerRepository.deleteAll();
        customerRepository.save(customer1);
        try {
            customerRepository.save(customer2);
        } catch (Exception e) {
            Assertions.assertTrue(true);
            return;
        }
        Assertions.assertTrue(false);

    }

    @Test
    public void shouldReturnTwoCustomer() {
        Customer customer1 = new Customer("Eva", "Chochoi", "eva.chochoi@gmail.com");
        Customer customer2 = new Customer("Eva", "Chochoi", "eva.chochoi1@gmail.com");

        customerRepository.deleteAll();
        customerRepository.save(customer1);
        customerRepository.save(customer2);

        List<Customer> customers = customerRepository.findAll();
        Assertions.assertEquals(2, customers.size());
    }

    @Test
    public void shouldDeleteCustomer() {
        customerRepository.deleteAll();
        Customer customer1 = new Customer("Eva", "Chochoi", "eva.chochoi@gmail.com");
        customerRepository.save(customer1);
        customerRepository.deleteByeMail("eva.chochoi@gmail.com");
        Customer customerFromDb = customerRepository.findByeMail("eva.chochoi@gmail.com");
        Assertions.assertNull(customerFromDb);
    }
}
