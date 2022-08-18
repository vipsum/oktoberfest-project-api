package net.oktoberfest.model.client.request;

import lombok.Data;
import java.util.List;

import net.oktoberfest.model.entities.BeerBrand;
import net.oktoberfest.model.entities.Person;

@Data
public class PersonRequest {

    private int weight;
    private Double alcoholToleranceInBlood;
    private boolean likesMusic;
    private List<Long> preferredBeerBrand;

    public Person construct(List<BeerBrand> beerBrandList) {

        return new Person(this, beerBrandList);
    }
}
