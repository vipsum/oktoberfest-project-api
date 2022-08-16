package net.oktoberfest.services;

import java.util.Arrays;
import java.util.List;

// import net.oktoberfest.model.entities.Person;
import net.oktoberfest.model.entities.BeerBrand;
import net.oktoberfest.model.entities.BeerJug;
import net.oktoberfest.model.entities.Person;
import net.oktoberfest.model.entities.Tent;

public interface TentService {
    Tent createTentAndBeerJug(Tent tent, BeerJug beerJug);

    Tent createReservation(long tent_id, long person_id);

    Tent getTentByIdForPerson(long id);

    List<Person> getAllReservations(long tent_id);

    List<Tent> getAllTentsForPerson(Long person_id);

    boolean checkAlcoholInBlood(Person person);

    boolean checkMaxCapacity(Tent tent);

    void checkIfPersonAlreadyInTent(Person person);

    Tent addPersonToTent(Long tent_id, Long person_id);

//    List<BeerBrand> getPersonPreferredBeerBrands(Person person);
    List<Tent> getTentsForPersonByPreferences(Long person_id);


    boolean checkMatchInTentByPreferences(Tent tent, Person person);



}
