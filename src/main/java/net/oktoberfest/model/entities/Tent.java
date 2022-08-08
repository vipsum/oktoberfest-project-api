package net.oktoberfest.model.entities;



import lombok.Data;
import lombok.NoArgsConstructor;
import net.oktoberfest.model.client.request.TentRequest;
import net.oktoberfest.model.client.response.TentResponse;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Tent")
public class Tent {
    @Id
    @Column(name = "tent_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    private int maxCapacity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_tent")
    private List<Person> currentOccupation;

    private boolean music;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "beer_in_tent")
    private BeerJug beerJug;

    // List of people who will be in the reservation list. If ID '1'
    // is on the list, the person whose that id corresponds to will be able to
    // enter the tent. Many people may have reservations, which is why it is a list
    // of people.

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "reserved_tent")
    private List<Person> reservation;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bought_beerjugs")
    private List<BeerJug> boughtBeerJugs;

    public Tent(TentRequest tentRequest) {

        this.maxCapacity = tentRequest.getMaxCapacity();
        //this.currentOccupation = tentRequest.getCurrentOccupation();
        this.music = tentRequest.isMusic();
        //this.beerJug = tentRequest.getBeerJug();
       // this.reservation = tentRequest.getReservation();

    }

    public Tent(List<Person> currentOccupation, int maxCapacity, boolean music, BeerJug beerJug,
            List<Person> reservation,List<BeerJug> boughtBeerJugs) {
        this.maxCapacity = maxCapacity;
        this.currentOccupation = currentOccupation;
        this.music = music;
        this.beerJug = beerJug;
        this.reservation = reservation;
        this.boughtBeerJugs = boughtBeerJugs;
    }

  
    
    public TentResponse response() {
        return new TentResponse(this);
    }

    @Override
    public String toString() {
        return "Register success. Tent Id: " + id;
    }
}
