package ru.dkalchenko.tacocloud.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dkalchenko.tacocloud.model.Person;
import ru.dkalchenko.tacocloud.repository.PersonRepository;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public void save(Person person) {
        personRepository.save(person);
    }
}
