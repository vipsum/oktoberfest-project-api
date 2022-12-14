package net.oktoberfest.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

import net.oktoberfest.model.client.request.PersonRequest;
import net.oktoberfest.model.client.response.PersonResponse;

@Data
@Entity
@Table(name = "Person")
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int weight;
    // aguante
    private Double alcoholToleranceInBlood;

    private boolean likesMusic;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private List<BeerBrand> preferredBeerBrand;

    public Person(PersonRequest personRequest, List<BeerBrand> beerBrandList) {
        this.weight = personRequest.getWeight();
        this.alcoholToleranceInBlood = personRequest.getAlcoholToleranceInBlood();
        this.likesMusic = personRequest.isLikesMusic();
        this.preferredBeerBrand = beerBrandList;
    }

    public Person(int weight, Double alcoholToleranceInBlood, boolean likesMusic, List<BeerBrand> preferredBeerBrand) {
        this.weight = weight;
        this.alcoholToleranceInBlood = alcoholToleranceInBlood;
        this.likesMusic = likesMusic;
        this.preferredBeerBrand = preferredBeerBrand;
    }

    public PersonResponse response() {
        return new PersonResponse(this);
    }

    @Override
    public String toString() {
        return "Your person ID is: " + id;
    }
}
