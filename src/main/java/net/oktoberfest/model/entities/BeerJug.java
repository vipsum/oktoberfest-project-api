package net.oktoberfest.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import net.oktoberfest.model.client.request.BeerJugRequest;
import net.oktoberfest.model.client.response.BeerJugResponse;

import javax.persistence.*;


@Data
@Entity
@Table(name = "BeerJug")
@AllArgsConstructor
public class BeerJug {
    @Id
    @Column(name = "BeerJugId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "BeerJugSize")
    private Double beerJugSize;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "beer_brand_id")
    private BeerBrand beerBrand;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "beerjug_owner")
    private Person owner;

    public BeerJug (BeerJugRequest beerJugRequest){

        this.beerJugSize = beerJugRequest.getBeerJugSize();
        this.beerBrand = beerJugRequest.getBeerBrand();
        this.owner = beerJugRequest.getOwner();

    }

    

    public BeerJug(Double beerJugSize, BeerBrand beerBrand, Person owner){
        this.beerJugSize = beerJugSize;
        this.beerBrand = beerBrand;
        this.owner = owner;
    }

    public BeerJugResponse response() {
        return new BeerJugResponse(this);
    }

    @Override
    public String toString() {return "BeerJug Id: " + id;
    }
}

