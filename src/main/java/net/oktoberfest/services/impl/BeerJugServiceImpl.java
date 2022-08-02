package net.oktoberfest.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.oktoberfest.model.entities.BeerJug;
import net.oktoberfest.repository.BeerJugRepository;

@Service
public class BeerJugServiceImpl {
    @Autowired
    private BeerJugRepository beerJugRepository;
    
    public BeerJug createBeerJug(BeerJug beerJug){
        return beerJugRepository.save(beerJug);
    }
}
