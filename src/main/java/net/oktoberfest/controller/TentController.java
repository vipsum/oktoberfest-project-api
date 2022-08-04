package net.oktoberfest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.oktoberfest.model.client.request.TentRequest;
import net.oktoberfest.model.client.response.ShowTentResponse;
import net.oktoberfest.model.client.response.TentResponse;
import net.oktoberfest.services.PersonService;
import net.oktoberfest.services.TentService;

@RestController
@RequestMapping("/Tent")
@AllArgsConstructor
public class TentController {

    private final TentService tentService;
    private final PersonService personService;

    @PostMapping
    public ResponseEntity<TentResponse> createTent(
            @RequestBody TentRequest tentRequest) {

        return new ResponseEntity<>(
                this.tentService.createTent(tentRequest.construct()).response(),
                HttpStatus.OK);
    }
    
    // @GetMapping("/showTents/{id}")
    // public ResponseEntity<ShowTentResponse> findById() {

    //     return new ResponseEntity<>(
    //         this.personService.findById(id), HttpStatus.OK);
    // }

    /*
     *     @GetMapping("/findByDni/{dni}")
    public Person findByDni(@PathVariable int dni){
        return personRepository.findByDni(dni);
    }
     */


    // @PostMapping
    // public ResponseEntity<TentResponse> enterTent(
    //     @RequestBody TentRequest tentRequest) {
    //         return n
    //     }
}
