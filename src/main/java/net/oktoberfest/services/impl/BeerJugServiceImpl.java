package net.oktoberfest.services.impl;

//import java.util.List;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import net.oktoberfest.model.entities.BeerJug;
//import net.oktoberfest.model.entities.Person;
import net.oktoberfest.repository.BeerJugRepository;

@Service
@AllArgsConstructor
public class BeerJugServiceImpl {
    
    private BeerJugRepository beerJugRepository;
    
    public BeerJug createBeerJug(BeerJug beerJug){
        return beerJugRepository.save(beerJug);
    }

    /*public Double calcAlcohoInBlood(Person owner) {
        List<BeerJug> jugs = beerJugRepository.findAllByOwner(owner);

        //
    } /* */

}
