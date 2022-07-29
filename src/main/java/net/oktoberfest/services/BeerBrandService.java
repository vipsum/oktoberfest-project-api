package net.oktoberfest.services;

import net.oktoberfest.model.entities.BeerBrand;

import java.util.List;

public interface BeerBrandService {
    BeerBrand createBeerBrand(BeerBrand beerBrand);
    List<BeerBrand> getBeerBrandsFromIdList(List<Long> beerBrandIdList);
}
