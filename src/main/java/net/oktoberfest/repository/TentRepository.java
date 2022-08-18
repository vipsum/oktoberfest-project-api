package net.oktoberfest.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.oktoberfest.model.entities.Tent;

@Repository
public interface TentRepository extends JpaRepository<Tent, Long> {

    Tent findById(long id);

    List<Tent> findAll();

    List<Tent> findAllByMusic(boolean LikesMusic);

}
