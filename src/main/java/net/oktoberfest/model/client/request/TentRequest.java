package net.oktoberfest.model.client.request;

import java.util.List;

import lombok.Data;
// import net.oktoberfest.model.entities.BeerJug;
// import net.oktoberfest.model.entities.Person;
import net.oktoberfest.model.entities.Tent;

@Data
public class TentRequest {

    private int maxCapacity;
    private List<Long> currentOccupation;
    private boolean music;
    private Integer beerJug;
   // private List<Long> reservation;
    

    public Tent construct() {
        return new Tent(this);
    }
}
