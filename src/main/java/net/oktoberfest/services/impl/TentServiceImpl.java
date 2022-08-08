package net.oktoberfest.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.oktoberfest.model.entities.BeerBrand;
import net.oktoberfest.model.entities.BeerJug;
import net.oktoberfest.model.entities.Person;
import net.oktoberfest.model.entities.Tent;
import net.oktoberfest.repository.PersonRepository;
import net.oktoberfest.repository.TentRepository;
import net.oktoberfest.services.BeerBrandService;
import net.oktoberfest.services.BeerJugService;
import net.oktoberfest.services.PersonService;
import net.oktoberfest.services.TentService;

@Service
@RequiredArgsConstructor
public class TentServiceImpl implements TentService {
    
    private TentRepository tentRepository;
    private PersonService personService;
    private BeerJugService beerJugService;
    private BeerBrandService beerBrandService;

    public  Tent createTent(Tent tent){

        return tentRepository.save(tent);
    }

    public Tent getTentByIdForPerson (long id) {
        
        return tentRepository.findById(id);

    }  

    public List<Tent> getAllTentsForPerson(Long person_id){
       
        return tentRepository.findAll();
    }

    public Tent addPersonToTent( Long tent_id,Long person_id){
        Double beerJugSize = (double) 500;
        BeerBrand beerBrand = beerBrandService.getBeerBrandByBeerName("Patagonia");
        
        //grabbing person and tent ids
        Person person = personService.getPersonById(person_id);
        Tent tent = getTentByIdForPerson(tent_id);
        //getting list of persons in db through personList
        List<Person> personList = tent.getCurrentOccupation();
        //adding my new person to the list
        personList.add(person);
        //setting the currentOccupation to personList
        tent.setCurrentOccupation(personList);
       
        BeerJug beerJug = new BeerJug(beerJugSize, beerBrand, person);
        beerJug = beerJugService.createBeerJug(beerJug);
        List<BeerJug> beerJugList = tent.getBoughtBeerJugs();
        beerJugList.add(beerJug);
        tent.setBoughtBeerJugs(beerJugList);
        //saving the tent 
        tentRepository.save(tent);
        return tent;

    }

    // @Override
    // public List<Person> getAllPerson(Long tent_id) {
    //    Optional<Tent> tent = tentRepository.findById(tent_id);
    //    List<Person> personList = tent.get().getCurrentOccupation();
    //     return personList;
    // }




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
