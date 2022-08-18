package net.oktoberfest.model.client.response;

import net.oktoberfest.model.Response;
import net.oktoberfest.model.entities.BeerBrand;

public class BeerBrandResponse implements Response {

    public long id;
    public String beerName;
    public Double alcoholPercentage;

    public BeerBrandResponse(BeerBrand beerBrand) {
        
        this.id = beerBrand.getId();
        this.beerName = beerBrand.getBeerName();
        this.alcoholPercentage = beerBrand.getAlcoholPercentage();
    }
}
