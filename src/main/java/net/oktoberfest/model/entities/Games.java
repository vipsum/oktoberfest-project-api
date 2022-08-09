package net.oktoberfest.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Games")
public class Games {
    @Id
    @Column(name = "GamesId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "GamesId")
    private List<Person> person;
    private int alcoholInBloodLimit;

    public Games (){

    }

    public Games(int alcoholInBloodLimit, List<Person> person){
        this.alcoholInBloodLimit = alcoholInBloodLimit;
        this.person = person;
    }

}




