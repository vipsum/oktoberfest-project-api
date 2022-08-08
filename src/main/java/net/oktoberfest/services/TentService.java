package net.oktoberfest.services;

import java.util.List;

// import net.oktoberfest.model.entities.Person;
import net.oktoberfest.model.entities.Tent;

public interface TentService {
    Tent createTent(Tent tent);

    Tent getTentByIdForPerson(long id);

    List<Tent> getAllTentsForPerson(Long person_id);

    Tent addPersonToTent(Long tent_id,Long person_id);

    // List<Person> getAllPerson(Long tent_id);

    
}
