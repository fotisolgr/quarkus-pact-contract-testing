package com.example;

import com.example.model.Person;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PersonServiceImpl implements PersonService {

    List<Person> persons = new ArrayList<>();

    @Override
    public Person getPerson() {
        return new Person("Fotis", 29);
    }

    @Override
    public Person addPerson() {
        final var personToAdd = new Person("Kostas", 32);
        persons.add(personToAdd);

        return personToAdd;
    }
}
