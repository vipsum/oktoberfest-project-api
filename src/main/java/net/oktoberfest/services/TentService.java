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

    Tent getTentByIdForPerson(long id);

    List<Tent> getAllTentsForPerson(Long person_id);

    Tent addPersonToTent(Long tent_id, Long person_id);

//    List<BeerBrand> getPersonPreferredBeerBrands(Person person);
    List<Tent> getTentsForPersonByPreferences(Long person_id);

    boolean checkMatchInTentByPreferences(Long tent_id, Long person_id);

    boolean checkMaxCapacity(long tent_id);

}
