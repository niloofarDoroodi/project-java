package homeservice.src.main.java.service;


import homeservice.src.main.java.entity.Person;

public interface PersonService {
    Person findByUsername(String username);
}
