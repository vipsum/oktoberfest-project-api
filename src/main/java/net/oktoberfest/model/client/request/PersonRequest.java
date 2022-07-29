package net.oktoberfest.model.client.request;

import lombok.Data;
import net.oktoberfest.model.entities.BeerBrand;
import net.oktoberfest.model.entities.Person;
import net.oktoberfest.services.impl.BeerBrandServiceImpl;
import java.util.List;

@Data
public class PersonRequest {
    private int weight;
    private int aguante;
    private boolean likesMusic;
    private List<Long> preferredBeerBrand;

    public Person construct(List<BeerBrand> beerBrandList){

        return new Person(this, beerBrandList);
    }


}
