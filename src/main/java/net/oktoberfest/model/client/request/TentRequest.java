package net.oktoberfest.model.client.request;

import java.util.List;
import lombok.Data;

import net.oktoberfest.model.entities.Tent;

@Data
public class TentRequest {

    private int maxCapacity;
    private boolean music;
    private BeerJugRequest beerJug;
    private List<Long> reservation;

    public Tent construct() {
        return new Tent(this);
    }
}
