package net.oktoberfest.services.impl;

import net.oktoberfest.model.entities.Person;
import net.oktoberfest.repository.PersonRepository;
import net.oktoberfest.services.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private  PersonRepository personRepository;

    public  Person createPerson(Person person){

        return personRepository.save(person);

    }


}
