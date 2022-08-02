package net.oktoberfest.model.client.request;

import lombok.Data;

import net.oktoberfest.model.entities.BeerBrand;
import net.oktoberfest.model.entities.BeerJug;

@Data
public class BeerJugRequest {
        private Double beerJugSize;
        private BeerBrand beerBrand;

        public BeerJug construct() {
            return new BeerJug(this);
        }
}
