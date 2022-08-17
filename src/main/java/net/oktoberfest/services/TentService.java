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

    Tent addReservation(long tent_id, long person_id);

    Tent getTentByIdForPerson(long id);

    Tent getAllReservations(long tent_id);

    List<Tent> getAllTentsForPerson(Long person_id);

    boolean checkMaxReservations(Tent tent);
    boolean checkAlcoholInBlood(Person person);

    boolean checkMaxCapacity(Tent tent);

    void checkIfPersonAlreadyInTent(Person person);


    Tent addPersonToTent(Long tent_id, Long person_id);

    List<Tent> getTentsForPersonByPreferences(Long person_id);

    boolean checkIfTentHasReservation(Tent tent);
    boolean checkIfPersonHasReservation(Tent tent, Person person);

    boolean checkMatchInTentByPreferences(Tent tent, Person person);



}
