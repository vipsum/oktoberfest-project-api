package net.oktoberfest.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.oktoberfest.model.client.request.BeerBrandRequest;
import net.oktoberfest.model.client.response.BeerBrandResponse;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "BeerBrand")
public class BeerBrand {
    @Id
    @Column(name = "beerBrandId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String beerName;

    private Double alcoholPercentage;

    public BeerBrand(BeerBrandRequest beerBrandRequest) {
        this.beerName = beerBrandRequest.getBeerName();
        this.alcoholPercentage = beerBrandRequest.getAlcoholPercentage();
    }

    public BeerBrand(String beerName, double alcoholPercentage) {
        this.beerName = beerName;
        this.alcoholPercentage = alcoholPercentage;
    }

    public BeerBrandResponse response(){
        return new BeerBrandResponse(this);
    }

    @Override
    public String toString() {
        return "Register success. Your Beer ID is: " + id;
    }
}
