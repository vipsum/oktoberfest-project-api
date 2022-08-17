package net.oktoberfest.services.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import net.oktoberfest.repository.BeerBrandRepository;
import net.oktoberfest.repository.BeerJugRepository;
import org.springframework.stereotype.Service;
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
    private final BeerJugRepository beerJugRepository;
    private final BeerBrandService beerBrandService;

    public Tent createTentAndBeerJug(Tent tent, BeerJug beerJug) {

        tent.setBeerJug(beerJug);

        return tentRepository.save(tent);
    }



    public Tent getTentByIdForPerson(long id) {

        return tentRepository.findById(id);
    }

    @Override
    public Tent getAllReservations(long tent_id) {

        return getTentByIdForPerson(tent_id);
    }

    public List<Tent> getAllTentsForPerson(Long person_id) {

        return tentRepository.findAll();
    }

    @Override
    public boolean checkMaxReservations(Tent tent) {
        int tentMaxCapacity = tent.getMaxCapacity();
        int tentReservations = tent.getReservation().size();

        return tentReservations != tentMaxCapacity;
    }

    @Override
    public boolean checkMatchInTentByPreferences(Tent tent, Person person) {
        //getting tent attributes
        boolean tentWithMusic = tent.isMusic();
        BeerBrand tentBeerBrand = tent.getBeerJug().getBeerBrand();
        //getting person  preferences
        boolean personLikesMusic = person.isLikesMusic();
        List<BeerBrand> personPreferredBeerBrandsList = person.getPreferredBeerBrand();

        return tentWithMusic == personLikesMusic && personPreferredBeerBrandsList.contains(tentBeerBrand);
    }

    @Override
    public String enterGame(Long person_id) {
        Person person = personService.getPersonById(person_id);
        double alcoholInBloodLimit = 3;
        double alcoholInBlood = checkAlcoholInBlood(person);

        if(alcoholInBlood > 0 && alcoholInBlood < alcoholInBloodLimit) {
            return "Entraste al juego!";
        } else return "No cumplis los requsiitos para entrar al juego";

    }

    @Override
    public double checkAlcoholInBlood(Person person) {

        double alcoholInBlood = 0.0;
        List<BeerJug> boughtBeerJugs = beerJugRepository.findAllByOwner(person);
        for (BeerJug b: boughtBeerJugs ) {
            //getting beerbrand attributes
            BeerBrand beerBrand = beerBrandRepository.findById(b.getBeerBrand().getId());
            double alcoholInBeerJug = b.getBeerJugSize()/100 * beerBrand.getAlcoholPercentage();
            alcoholInBlood += alcoholInBeerJug;
        }

        return alcoholInBlood / person.getWeight();
    }

    @Override
    public boolean checkMaxCapacity(Tent tent) {

        int tentMaxCapacity = tent.getMaxCapacity();
        int tentCurrentOccupationSize = tent.getCurrentOccupation().size();

        return tentCurrentOccupationSize != tentMaxCapacity;
    }

    @Override
    public void checkIfPersonAlreadyInTent(Person person) {
        List<Tent> allTents = tentRepository.findAll();

        for (Tent t: allTents) {
            List<Person> tentCurrentOccupation = t.getCurrentOccupation();
            if (tentCurrentOccupation.contains(person)) {
                tentCurrentOccupation.remove(person);
                t.setCurrentOccupation(tentCurrentOccupation);
                tentRepository.save(t);
            }
        }
    }


    @Override
    public List<Tent> getTentsForPersonByPreferences(Long person_id) {
        Person person = personService.getPersonById(person_id);

        //getting person  preferences
        boolean personLikesMusic = person.isLikesMusic();
        List<BeerBrand> personPreferredBeerBrandsList = person.getPreferredBeerBrand();

        //finding all tents with person music preferences
        List<Tent> tentsWithPersonMusicPreferences = tentRepository.findAllByMusic(personLikesMusic);
        List<Tent> tentsWithPersonPreferences = new ArrayList<>();

        for (Tent t : tentsWithPersonMusicPreferences) {
            for (BeerBrand b : personPreferredBeerBrandsList) {
                if (b.equals(t.getBeerJug().getBeerBrand()))
                    tentsWithPersonPreferences.add(t);
            }
        }
        return tentsWithPersonPreferences.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public boolean checkIfPersonAndTentHaveReservation(Tent tent, Person person) {

            int tentReservationsSize = tent.getReservation().size();
            List<Person> tentReservations = tent.getReservation();

            if (tentReservationsSize > 0) {
                boolean personInTentReservation = tentReservations.contains(person);
                return personInTentReservation;
            }
            return true;
    }


    public Tent addPersonToTent(Long tent_id, Long person_id) {

        //grabbing person and tent ids
        Person person = personService.getPersonById(person_id);
        Tent tent = getTentByIdForPerson(tent_id);

        //getting list of persons in db through personList
        List<Person> personList = tent.getCurrentOccupation();

        boolean checkMatchInTentByPreferences = checkMatchInTentByPreferences(tent, person);
        boolean checkMaxCapacity = checkMaxCapacity(tent);
        boolean checkAlcoholInBlood = checkAlcoholInBlood(person) <= person.getAlcoholToleranceInBlood();
        boolean checkIfPersonAndTentHaveReservation = checkIfPersonAndTentHaveReservation(tent, person);

        checkIfPersonAlreadyInTent(person);
        if (checkMatchInTentByPreferences  && checkMaxCapacity && checkAlcoholInBlood && checkIfPersonAndTentHaveReservation) {
            //adding my new person to the list
            personList.add(person);

            if (tent.getReservation().size() > 0) {
                BeerJug welcomeBeerJug = new BeerJug(300.0,
                        tent.getBeerJug().getBeerBrand(),
                        person);
                tent.getBoughtBeerJugs().add(welcomeBeerJug);
            }

            //setting the currentOccupation to personList
            tent.setCurrentOccupation(personList);
            tent.getBeerJug().setOwner(person);

            tent.getBoughtBeerJugs().add(tent.getBeerJug());


            //saving the tent
            tentRepository.save(tent);
        } else throw new RuntimeException("something failed here");
        return tent;


    }
    @Override
    public Tent addReservation(long tent_id, long person_id) {
        //grabbing person and tent ids
        Person person = personService.getPersonById(person_id);
        Tent tent = getTentByIdForPerson(tent_id);

        //getting list of persons in db through personList
        List<Person> reservationList = tent.getReservation();

        boolean checkMatchInTentByPreferences = checkMatchInTentByPreferences(tent, person);
        boolean checkMaxReservations = checkMaxReservations(tent);

        if (checkMatchInTentByPreferences  && checkMaxReservations) {

            reservationList.add(person);
            //setting the reservations to reservationList
            tent.setReservation(reservationList);
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
