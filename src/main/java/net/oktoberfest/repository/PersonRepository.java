package net.oktoberfest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.oktoberfest.model.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}