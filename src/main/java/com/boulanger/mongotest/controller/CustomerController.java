package com.boulanger.mongotest.controller;

import com.boulanger.mongotest.model.Customer;
import com.boulanger.mongotest.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/customer")
    public @ResponseBody List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}
