package net.oktoberfest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.oktoberfest.model.entities.BeerJug;


@Repository
public interface BeerJugRepository extends JpaRepository<BeerJug, Long>{
    
}
