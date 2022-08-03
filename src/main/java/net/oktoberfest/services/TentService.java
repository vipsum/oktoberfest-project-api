package net.oktoberfest.services;

import net.oktoberfest.model.entities.Tent;

public interface TentService {
    Tent createTent(Tent tent);
    Tent findById(long id);
}
