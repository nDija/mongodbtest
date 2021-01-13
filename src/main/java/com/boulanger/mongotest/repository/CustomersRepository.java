package com.boulanger.mongotest.repository;

import com.boulanger.mongotest.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomersRepository extends MongoRepository<Customer, String> {

    public Customer findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);
    public Customer findByeMail(String eMail);
}
