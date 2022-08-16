package net.oktoberfest.services.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import net.oktoberfest.repository.BeerBrandRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

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
    private final BeerBrandRepository beerBrandRepository;
    private final PersonService personService;
    private final BeerJugService beerJugService;
    private final BeerBrandService beerBrandService;

    public Tent createTentAndBeerJug(Tent tent, BeerJug beerJug) {

        tent.setBeerJug(beerJug);

        return tentRepository.save(tent);
    }

    public Tent getTentByIdForPerson(long id) {

        return tentRepository.findById(id);
    }

    public List<Tent> getAllTentsForPerson(Long person_id) {

        return tentRepository.findAll();
    }


    @Override
    public List<Tent> getTentsForPersonByPreferences(Long person_id) {
        Person person = personService.getPersonById(person_id);

        //getting person  preferences
        boolean personLikesMusic = person.isLikesMusic();
//        List<BeerBrand> personPreferredBeerBrandsList = getPersonPreferredBeerBrands(person);
        List<BeerBrand> personPreferredBeerBrandsList = person.getPreferredBeerBrand();
        System.out.println(personPreferredBeerBrandsList);

        //finding all tents with person music preferences
        List<Tent> tentsWithPersonMusicPreferences = tentRepository.findAllByMusic(personLikesMusic);
        List<Tent> tentsWithPersonPreferences = new ArrayList<>(tentsWithPersonMusicPreferences);

        for (Tent t : tentsWithPersonMusicPreferences) {
            for (BeerBrand b : personPreferredBeerBrandsList) {
                if (b.equals(t.getBeerJug().getBeerBrand()))
                    tentsWithPersonPreferences.add(t);
            }
        }
        return tentsWithPersonPreferences.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public boolean checkMatchInTentByPreferences(Long tent_id, Long person_id) {
        Person person = personService.getPersonById(person_id);

        Tent tent = getTentByIdForPerson(tent_id);
        //getting tent attributes
        boolean tentWithMusic = tent.isMusic();
        BeerBrand tentBeerBrand = tent.getBeerJug().getBeerBrand();
        //getting person  preferences
        boolean personLikesMusic = person.isLikesMusic();
        List<BeerBrand> personPreferredBeerBrandsList = person.getPreferredBeerBrand();

        boolean matchInPreferences = false;
        if (tentWithMusic == personLikesMusic && personPreferredBeerBrandsList.contains(tentBeerBrand)) {
            matchInPreferences = true;
        }
        return matchInPreferences;
    }

    @Override
    public boolean checkMaxCapacity(long tent_id) {

        Tent tent = getTentByIdForPerson(tent_id);
        int tentMaxCapacity = tent.getMaxCapacity();
        int tentCurrentOcuppationSize = tent.getCurrentOccupation().size();

        boolean maxCapacity = false;
        if (tentCurrentOcuppationSize != tentMaxCapacity) {
            maxCapacity = true;
        }
        return maxCapacity;
    }

    public Tent addPersonToTent(Long tent_id, Long person_id) {

        //grabbing person and tent ids
        Person person = personService.getPersonById(person_id);
        Tent tent = getTentByIdForPerson(tent_id);

        //getting list of persons in db through personList
        List<Person> personList = tent.getCurrentOccupation();

        boolean checkMatchInTentByPreferences = checkMatchInTentByPreferences(tent_id, person_id);
        boolean checkMaxCapacity = checkMaxCapacity(tent_id);

        if (checkMatchInTentByPreferences  && checkMaxCapacity) {
            //adding my new person to the list
            personList.add(person);

            //setting the currentOccupation to personList
            tent.setCurrentOccupation(personList);
            tent.getBeerJug().setOwner(person);
            tent.getBoughtBeerJugs().add(tent.getBeerJug());

            //saving the tent
            tentRepository.save(tent);
        } else throw new RuntimeException("something failed here");
        return tent;

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
