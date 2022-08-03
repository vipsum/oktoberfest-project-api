package net.oktoberfest.services.impl;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import java.util.List;

import net.oktoberfest.model.entities.BeerBrand;
import net.oktoberfest.repository.BeerBrandRepository;
import net.oktoberfest.services.BeerBrandService;

@Service
@AllArgsConstructor
public class BeerBrandServiceImpl implements BeerBrandService {
    
    private BeerBrandRepository beerBrandRepository;

    public BeerBrand createBeerBrand(BeerBrand beerBrand){

        return beerBrandRepository.save(beerBrand);

    }

    public List<BeerBrand> getBeerBrandsFromIdList(List<Long> beerBrandIdList){

       return beerBrandRepository.findAllById_In(beerBrandIdList);

    }

}
