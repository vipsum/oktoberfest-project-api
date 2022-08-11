package net.oktoberfest.services;


import net.oktoberfest.model.client.request.BeerJugRequest;
import net.oktoberfest.model.entities.BeerBrand;
import net.oktoberfest.model.entities.BeerJug;
//import net.oktoberfest.model.entities.Person;

public interface BeerJugService {

    BeerJug createBeerJug(BeerJugRequest beerJugRequest);
   /*
 public Double calcAlcoholInBlood(Person owner);
 BeerJug getBeerJugById(long id);
*/

    BeerJug findById(Long id);
}
