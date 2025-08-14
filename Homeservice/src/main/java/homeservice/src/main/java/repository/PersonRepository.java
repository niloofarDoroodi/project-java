package homeservice.src.main.java.repository;


import homeservice.src.main.java.entity.Person;

import java.util.Optional;

public interface PersonRepository {
    Optional<Person> findByUsername(String username);
}
