package com.boulanger.mongotest.repository;

import com.boulanger.mongotest.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByeMail(String eMail);

    Customer deleteByeMail(String eMail);
}
