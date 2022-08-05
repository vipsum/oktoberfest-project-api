package net.oktoberfest.services.impl;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import net.oktoberfest.model.entities.Person;
import net.oktoberfest.repository.PersonRepository;
import net.oktoberfest.services.PersonService;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {
   
    private  PersonRepository personRepository;

    public  Person createPerson(Person person){

        return personRepository.save(person);

    }

    public  Person getPersonById(long id) {

        return personRepository.findById(id);

    }


}
