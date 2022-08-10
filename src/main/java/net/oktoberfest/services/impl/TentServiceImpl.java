package net.oktoberfest.services.impl;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import net.oktoberfest.model.entities.BeerBrand;
import net.oktoberfest.model.entities.BeerJug;
import net.oktoberfest.model.entities.Person;
import net.oktoberfest.model.entities.Tent;
import net.oktoberfest.repository.TentRepository;
import net.oktoberfest.services.BeerBrandService;
import net.oktoberfest.services.BeerJugService;
import net.oktoberfest.services.PersonService;
import net.oktoberfest.services.TentService;

@Service
@AllArgsConstructor
public class TentServiceImpl implements TentService {
    
    private final TentRepository tentRepository;
    private final PersonService personService;
    private final BeerJugService beerJugService;
    private final BeerBrandService beerBrandService;

    public  Tent createTentAndBeerJug(Tent tent, BeerJug beerJug){

        tent.setBeerJug(beerJug);
        return tentRepository.save(tent);
    }

    public Tent getTentByIdForPerson (long id) {
        
        return tentRepository.findById(id);

    }  

    public List<Tent> getAllTentsForPerson(Long person_id){
       
        return tentRepository.findAll();

    }

    public Tent addPersonToTent( Long tent_id,Long person_id){
        
        //grabbing person and tent ids
        Person person = personService.getPersonById(person_id);
        Tent tent = getTentByIdForPerson(tent_id);

        //getting list of persons in db through personList
        List<Person> personList = tent.getCurrentOccupation();

        //adding my new person to the list
        personList.add(person);

        //setting the currentOccupation to personList
        tent.setCurrentOccupation(personList);
        tent.getBeerJug().setOwner(person);
        tent.getBoughtBeerJugs().add(tent.getBeerJug());

        //saving the tent 
        tentRepository.save(tent);
        return tent;

    }

    @Override
    public List<BeerBrand> getPersonPreferredBeerBrands(long person_id) {

        return personService.getPersonById(person_id).getPreferredBeerBrand();
    }

//    @Override
//    public List<Tent> getTentsForPersonByPreferredBeerBrand(Long personId) {
//
//        List<Tent> allTents = getAllTentsForPerson(personId);
//        Person person = personService.getPersonById(personId);
//
//        List<BeerBrand> personPreferredBeerBrandsList = getPersonPreferredBeerBrands(personId);
//
//
//       return null;
//    }

    @Override
    public List<Tent> getTentsForPersonByPreferences(Long person_id) {
        //getting person by id
        Person person = personService.getPersonById(person_id);
        //getting person  preferences
        boolean personLikesMusic = person.isLikesMusic();
        List<BeerBrand> personPreferredBeerBrandsList = getPersonPreferredBeerBrands(person_id);

        List<Tent> tentsWithPersonMusicPreferences =  tentRepository.findAllByMusic(personLikesMusic);
//        List<String> namesList = personList.stream()
//                .map(Person::getName)
//                .collect(Collectors.toList());
        List<Tent> tentsWithPersonPreferences = new ArrayList<>();

;        for (Tent t: tentsWithPersonMusicPreferences) {
            for (BeerBrand b: personPreferredBeerBrandsList) {
                if(b.equals(t.getBeerJug().getBeerBrand()))
                        tentsWithPersonPreferences.add(t);
            }
        }


//                stream()
//                .filter(tent -> tent.getBeerJug().getBeerBrand().equals())


    //    List<Tent> tentsWithPersonPreferredBeerBrand = getTentsForPersonByPreferredBeerBrand(person_id);



        return null;
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
