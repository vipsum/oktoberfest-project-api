package net.oktoberfest.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Tent")
public class Tent {
    @Id
    @Column(name = "tent_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int maxCapacity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_tent")
    public List<Person> currentOccupation;

    private boolean music;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "beer_in_tent")
    private BeerJug beerJug;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "reserved_tent")
    private List<Person> reservation;


    public Tent(){

    }

    public Tent(List<Person> currentOccupation, int maxCapacity, boolean music, BeerJug beerJug, List<Person> reservation){
        this.maxCapacity = maxCapacity;
        this.currentOccupation = currentOccupation;
        this.music = music;
        this.beerJug = beerJug;
        this.reservation = reservation;
    }

    @Override
    public String toString() {
        return "Register success. Tent Id: " + id;
    }
}

