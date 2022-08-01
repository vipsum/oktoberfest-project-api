package net.oktoberfest.model.client.request;

import lombok.Data;
import net.oktoberfest.model.entities.BeerBrand;

@Data
public class BeerBrandRequest {
    
    private String beerName;
    private Double alcoholPercentage;

    public BeerBrand construct() {
        return new BeerBrand(this);
    }
}
