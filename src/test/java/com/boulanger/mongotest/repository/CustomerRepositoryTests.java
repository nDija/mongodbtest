package com.boulanger.mongotest.repository;

import com.boulanger.mongotest.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static java.lang.String.format;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@ContextConfiguration(initializers = CustomerRepositoryTests.Initializer.class)
@Testcontainers
public class CustomerRepositoryTests {

    @Autowired
    public CustomerRepository customerRepository;

    //anotation container that are shared between all methods of a test class
    @Container
    public static GenericContainer mongoContainer = new GenericContainer("mongo:4.4.2");

    //properties for connecting to docker database
    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {

            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(configurableApplicationContext,
                    format("spring.data.mongodb.uri=mongodb://%s:%s/test", mongoContainer.getContainerIpAddress(), mongoContainer.getMappedPort(27017)));
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(configurableApplicationContext,
                    "spring.data.mongodb.auto-index-creation=true");
        }
    }

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
