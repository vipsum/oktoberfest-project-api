package net.oktoberfest.model.client.response;

import java.util.List;

import net.oktoberfest.model.Response;
import net.oktoberfest.model.entities.BeerJug;
import net.oktoberfest.model.entities.Person;
import net.oktoberfest.model.entities.Tent;

public class TentResponse implements Response {

    public long id;
    public int maxCapacity;
    public List<Person> currentOccupation;
    public boolean music;
    public BeerJug beerJug;
    public List<Person> reservation;

    public TentResponse(Tent tent) {
        this.id = tent.getId();
        this.maxCapacity = tent.getMaxCapacity();
        this.currentOccupation = tent.getCurrentOccupation();
        this.music = tent.isMusic();
        this.beerJug = tent.getBeerJug();
        this.reservation = tent.getReservation();
    }

}
