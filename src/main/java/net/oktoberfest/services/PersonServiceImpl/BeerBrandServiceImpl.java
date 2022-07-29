package net.oktoberfest.services.BeerBrandServiceImpl;

import net.oktoberfest.model.entities.BeerBrand;
import net.oktoberfest.repository.BeerBrandRepository;
import net.oktoberfest.services.BeerBrandService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerBrandServiceImpl implements BeerBrandService {
    private BeerBrandRepository beerBrandRepository;

    public BeerBrand createBeerBrand(BeerBrand beerBrand){

        return beerBrandRepository.save(beerBrand);

    }

    public List<BeerBrand> getBeerBrandsFromIdList(List<Long> beerBrandIdList){

       return beerBrandRepository.findAllById_In(beerBrandIdList);

    }

}
