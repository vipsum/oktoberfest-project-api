package net.oktoberfest.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.oktoberfest.model.entities.Person;
import net.oktoberfest.model.entities.Tent;
import net.oktoberfest.repository.PersonRepository;
import net.oktoberfest.repository.TentRepository;
import net.oktoberfest.services.PersonService;
import net.oktoberfest.services.TentService;

@Service
@AllArgsConstructor
public class TentServiceImpl implements TentService {
    
    private TentRepository tentRepository;
    private PersonService personService;

    public  Tent createTent(Tent tent){

        return tentRepository.save(tent);
    }

    public Tent getTentByIdForPerson (long id) {
        
        return tentRepository.findById(id);

    }  

    public List<Tent> getAllTentsForPerson(Long person_id){
       
        return tentRepository.findAll();
    }

    public Person addPersonToTent( Long tent_id,Long person_id){
        
        Person person = personService.getPersonById(person_id);
        Tent tent = getTentByIdForPerson(tent_id);
        List<Person> personList = getAllPerson(tent_id);
        personList.add(person);
        tent.setCurrentOccupation(personList);
        tentRepository.save(tent);
        return person;

    }

    @Override
    public List<Person> getAllPerson(Long tent_id) {
       Optional<Tent> tent = tentRepository.findById(tent_id);
       List<Person> personList = tent.get().getCurrentOccupation();
        return personList;
    }


    /*
 * public class TentServiceImpl implements TentService {

    private TentRepository tentRepository;
    private PersonRepository personRepository;

    public Tent createTent(TentRequest tentRequest) {
        Tent tent = new Tent();
        for (Long p: tentRequest.getCurrentOccupation()){
          Optional personOptional = personRepository.findById(p); 
          if(personOptional.isPresent())
            tent.getCurrentOccupation().add((Person) personOptional.get());
        }
        return tentRepository.save(tent);
    }

 */
}
