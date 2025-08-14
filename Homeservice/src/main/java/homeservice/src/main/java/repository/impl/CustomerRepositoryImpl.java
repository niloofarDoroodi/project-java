package homeservice.src.main.java.repository.impl;


import homeservice.src.main.java.basics.baseRepository.impl.BaseRepositoryImpl;
import homeservice.src.main.java.entity.Customer;

public class CustomerRepositoryImpl extends BaseRepositoryImpl<Customer> {

    public CustomerRepositoryImpl(Class<Customer> className) {
        super(className);
    }
}
