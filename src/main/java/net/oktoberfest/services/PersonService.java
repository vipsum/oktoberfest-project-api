package net.oktoberfest.services;

import net.oktoberfest.model.entities.Person;

public interface PersonService {
    Person createPerson(Person person);
    Person getPersonId(long id);
}
