package net.oktoberfest.services.impl;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

import net.oktoberfest.model.entities.BeerBrand;
import net.oktoberfest.repository.BeerBrandRepository;
import net.oktoberfest.services.BeerBrandService;

@Service
@AllArgsConstructor
public class BeerBrandServiceImpl implements BeerBrandService {

    private BeerBrandRepository beerBrandRepository;

    public BeerBrand createBeerBrand(BeerBrand beerBrand) {

        return beerBrandRepository.save(beerBrand);

    }

    public List<BeerBrand> getBeerBrandsFromIdList(List<Long> beerBrandIdList) {

        return beerBrandRepository.findAllById_In(beerBrandIdList);

    }

    public BeerBrand getBeerBrandByBeerName(String beerName){

        return beerBrandRepository.findByBeerName(beerName);
    }


    public BeerBrand getBeerBrandById(long id){
        return beerBrandRepository.findById(id);
    }

}
