package net.oktoberfest.model.client.response;

import net.oktoberfest.model.Response;
import net.oktoberfest.model.entities.BeerBrand;
import net.oktoberfest.model.entities.BeerJug;

public class BeerJugResponse implements Response {

    public long id;
    public Double beerJugSize;
    public BeerBrand beerBrand;

    public BeerJugResponse(BeerJug beerJug) {

        this.id = beerJug.getId();
        this.beerJugSize = beerJug.getBeerJugSize();
        this.beerBrand = beerJug.getBeerBrand();
    }

}
