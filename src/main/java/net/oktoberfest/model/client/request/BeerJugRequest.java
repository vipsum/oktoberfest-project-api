package net.oktoberfest.model.client.request;

import lombok.Data;

import net.oktoberfest.model.entities.BeerJug;

@Data
public class BeerJugRequest {

    private Double beerJugSize; // In milliliters

    private Long beerBrandId;

    public BeerJug construct() {

        return new BeerJug(this);
    }
}
