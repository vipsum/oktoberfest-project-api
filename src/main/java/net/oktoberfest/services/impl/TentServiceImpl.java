package net.oktoberfest.services.impl;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.oktoberfest.model.entities.Tent;
import net.oktoberfest.repository.TentRepository;
import net.oktoberfest.services.TentService;

@Service
@AllArgsConstructor
public class TentServiceImpl implements TentService {
    
    private TentRepository tentRepository;

    public Tent findById (long id) {
        
        return tentRepository.findById(id);

    }  
}
