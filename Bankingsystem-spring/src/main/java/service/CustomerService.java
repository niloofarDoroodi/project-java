package service;


import entity.Customer;
import exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CustomerRepository;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(String name, String email) {
        Customer customer = new Customer(name, email);
        return customerRepository.save(customer);
    }

    public Customer getCustomer(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            return customerOptional.get();
        } else {
            throw new CustomerNotFoundException("Customer not found");
        }
    }
}