package net.oktoberfest.model.client.response;

import net.oktoberfest.model.Response;
import net.oktoberfest.model.entities.BeerBrand;
import net.oktoberfest.model.entities.Person;

import java.util.List;

public class PersonResponse implements Response {

    public long id;
    public int weight;
    public int alcoholToleranceInBlood;
    public boolean likesMusic;
    public List<BeerBrand> preferredBeerBrand;

public PersonResponse(Person person) {

    this.id = person.getId();
    this.weight = person.getWeight();
    this.alcoholToleranceInBlood = person.getAlcoholToleranceInBlood();
    this.likesMusic = person.isLikesMusic();
    this.preferredBeerBrand = person.getPreferredBeerBrand();
}
}
