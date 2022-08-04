package net.oktoberfest.services;

import java.util.List;

import net.oktoberfest.model.entities.Tent;

public interface TentService {
    Tent createTent(Tent tent);

    Tent findById(long id);

    List<Tent> getAllTentsForPerson(Long person_id);
}
