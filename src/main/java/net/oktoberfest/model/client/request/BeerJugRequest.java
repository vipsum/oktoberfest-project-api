package net.oktoberfest.model.client.request;

import lombok.Data;

import net.oktoberfest.model.entities.BeerBrand;
import net.oktoberfest.model.entities.BeerJug;
import net.oktoberfest.model.entities.Person;

@Data
public class BeerJugRequest {
        private Double beerJugSize;
        private BeerBrand beerBrand;
        private Person owner;

        public BeerJug construct() {
            return new BeerJug(this);
        }
}
