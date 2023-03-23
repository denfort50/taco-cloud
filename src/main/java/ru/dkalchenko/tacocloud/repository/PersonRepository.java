package ru.dkalchenko.tacocloud.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dkalchenko.tacocloud.model.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

    Person findByUsername(String username);
}
