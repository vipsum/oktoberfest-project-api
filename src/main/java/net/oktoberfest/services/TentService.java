package net.oktoberfest.services;

import net.oktoberfest.model.client.request.TentRequest;
import net.oktoberfest.model.entities.Tent;

public interface TentService {
    Tent createTent(TentRequest tent);

    Tent findById(long id);
}
