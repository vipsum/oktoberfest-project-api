package net.oktoberfest.services.impl;

//import java.util.List;

import net.oktoberfest.model.client.request.BeerJugRequest;
import net.oktoberfest.model.entities.BeerBrand;
import net.oktoberfest.services.BeerBrandService;
import net.oktoberfest.services.BeerJugService;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import net.oktoberfest.model.entities.BeerJug;
import net.oktoberfest.repository.BeerJugRepository;

@Service
@AllArgsConstructor
public class BeerJugServiceImpl implements BeerJugService {
    
    private BeerJugRepository beerJugRepository;
    private BeerBrandService beerBrandService;

    public BeerJug createBeerJug(BeerJugRequest beerJugRequest){
        BeerJug beerJug = beerJugRequest.construct();
        BeerBrand beerBrand = beerBrandService.getBeerBrandById(beerJugRequest.getBeerBrandId());
        beerJug.setBeerBrand(beerBrand);
        return beerJugRepository.save(beerJug);
    }

    @Override
    public BeerJug findById(Long id) {
        return beerJugRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }


     /*public BeerJug getBeerJugById(long id) {
        return beerJugRepository.findById(id);
    }
   public Double calcAlcohoInBlood(Person owner) {
        List<BeerJug> jugs = beerJugRepository.findAllByOwner(owner);
    } /* */

}
