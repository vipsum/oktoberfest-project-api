package net.oktoberfest.model.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "BeerBrand")
public class BeerBrand {
    @Id
    @Column(name = "beerBrandId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String beerName;

    private Double alcoholPercentage;

 public BeerBrand(){

    }

    public BeerBrand (String beerName, Double alcoholPercentage){
     this.beerName = beerName;
     this.alcoholPercentage = alcoholPercentage;
    }

    @Override
    public String toString() {
        return "Register success. Your Beer ID is: " + id;
    }
}
