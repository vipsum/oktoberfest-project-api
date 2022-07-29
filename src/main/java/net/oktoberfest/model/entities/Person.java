package net.oktoberfest.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.oktoberfest.model.client.request.PersonRequest;
import net.oktoberfest.model.client.response.PersonResponse;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "Person")
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int weight;

    private int aguante;
    private boolean likesMusic;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private List<BeerBrand> preferredBeerBrand;



public Person(PersonRequest personRequest, List<BeerBrand> beerBrandList){
    this.weight = personRequest.getWeight();
    this.aguante = personRequest.getAguante();
    this.likesMusic = personRequest.isLikesMusic();
    this.preferredBeerBrand = personRequest.beerBrandList();
}

public Person(int weight, int aguante,  boolean likesMusic, List<BeerBrand> preferredBeerBrand){
    this.weight = weight;
    this.aguante = aguante;
    this.likesMusic = likesMusic;
    this.preferredBeerBrand = preferredBeerBrand;
}

public PersonResponse response (){
    return new PersonResponse(this);
}
@Override
    public String toString() {
    return "Register success. Your ID is: " + id;
}
}

