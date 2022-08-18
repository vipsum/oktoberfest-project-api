package net.oktoberfest.services;

import net.oktoberfest.model.entities.Games;
import net.oktoberfest.model.entities.Person;

public interface GamesService {

    Games createGame(Games games);

    String enterGame(Long person_id);

    double checkAlcoholInBloodAndBoughtBeers(Person person);

}
