package net.oktoberfest.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import net.oktoberfest.model.entities.BeerBrand;


@Repository
public interface BeerBrandRepository extends JpaRepository<BeerBrand, Long>{

    List<BeerBrand> findAllById_In(List<Long> beerBrandIdList);
    BeerBrand findBybeerName(String beerName);
}