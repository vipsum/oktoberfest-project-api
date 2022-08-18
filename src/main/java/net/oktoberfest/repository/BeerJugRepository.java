package net.oktoberfest.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.oktoberfest.model.entities.BeerJug;
import net.oktoberfest.model.entities.Person;

@Repository
public interface BeerJugRepository extends JpaRepository<BeerJug, Long> {

    List<BeerJug> findAllByOwner(Person owner);

    BeerJug findById(long id);
}
