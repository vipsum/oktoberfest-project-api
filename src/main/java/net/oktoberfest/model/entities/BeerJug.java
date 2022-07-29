package net.oktoberfest.model.entities;

import lombok.Data;
import javax.persistence.*;


@Data
@Entity
@Table(name = "BeerJug")
public class BeerJug {
    @Id
    @Column(name = "BeerJugId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "BeerJugSize")
    private int beerJugSize;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "beer_brand_id")
    public BeerBrand beerBrand;

    public BeerJug (){

    }

    public BeerJug(int beerJugSize, BeerBrand beerBrand){
        this.beerJugSize = beerJugSize;
        this.beerBrand = beerBrand;
    }
    @Override
    public String toString() {return "BeerJug Id: " + id;
    }
}

