package net.oktoberfest.model.client.request;

import java.util.List;

import lombok.Data;
import net.oktoberfest.model.entities.BeerJug;
import net.oktoberfest.model.entities.Person;
import net.oktoberfest.model.entities.Tent;

@Data
public class TentRequest {

    private int maxCapacity;
    private List<Person> currentOccupation;
    private boolean music;
    private BeerJug beerJug;
    private List<Person> reservation;

    public Tent construct() {
        return new Tent(this);
    }
}
