package net.oktoberfest.services;

import net.oktoberfest.model.entities.Tent;

public interface TentService {
    Tent findById(long id);
}
