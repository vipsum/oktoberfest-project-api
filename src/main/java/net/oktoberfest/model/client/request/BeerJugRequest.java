package net.oktoberfest.model.client.request;

import lombok.Data;

import net.oktoberfest.model.entities.BeerBrand;
import net.oktoberfest.model.entities.BeerJug;
import net.oktoberfest.model.entities.Person;

@Data
public class BeerJugRequest {
        //EN MILILITROS -> 500ML > 0.5 1000ML > 1
        private Double beerJugSize;

        private Long beerBrandId;

        public BeerJug construct() {

            return new BeerJug(this);
        }
}
