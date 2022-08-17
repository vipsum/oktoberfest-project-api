package net.oktoberfest.services.impl;

import lombok.AllArgsConstructor;
import net.oktoberfest.model.entities.Games;
import net.oktoberfest.model.entities.Person;
import net.oktoberfest.repository.GamesRepository;
import net.oktoberfest.repository.PersonRepository;
import net.oktoberfest.services.GamesService;
import net.oktoberfest.services.PersonService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GamesServiceImpl implements GamesService {
   
    private  PersonRepository personRepository;
    private GamesRepository gamesRepository;
    private PersonServiceImpl personService;

    @Override
    public Games createGame(Games game) {
        return gamesRepository.save(game);
    }

    @Override
    public String enterGame(Long person_id) {
        Person person = personService.getPersonById(person_id);
        Games personList =  p
        return null;
    }

    @Override
    public double checkAlcoholInBloodAndBoughtBeers(Person person) {

        return 0;
    }
}
