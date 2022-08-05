package net.oktoberfest.services;

import java.util.List;

import net.oktoberfest.model.entities.Tent;

public interface TentService {
    Tent createTent(Tent tent);

    Tent getTentByIdForPerson(long id);

    List<Tent> getAllTentsForPerson(Long person_id);

    // List<Tent> addPersonToTent(Long person_id, Long tent_id);
}
