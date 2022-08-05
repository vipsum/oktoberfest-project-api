package net.oktoberfest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.oktoberfest.model.client.request.TentRequest;
import net.oktoberfest.model.client.response.TentResponse;
import net.oktoberfest.model.entities.Tent;
import net.oktoberfest.services.PersonService;
import net.oktoberfest.services.TentService;

@RestController
@RequestMapping("/tents")
@AllArgsConstructor
public class TentController {

    private final TentService tentService;
    private final PersonService personService;

    @PostMapping("/create")
    public ResponseEntity<TentResponse> createTent(
            @RequestBody TentRequest tentRequest) {

        return new ResponseEntity<>(
                this.tentService.createTent(tentRequest.construct()).response(),
                HttpStatus.OK);
    }
    
    @GetMapping("/{personId}")
    public ResponseEntity<List<TentResponse>> getAllTentsForPerson(
            @PathVariable Long personId) {

        return new ResponseEntity<>(
                this.tentService.getAllTentsForPerson(personId)
                .stream()
                .map(Tent::response)
                .collect(Collectors.toList())
                , HttpStatus.OK);
    }

    @PostMapping ("/enterTent")
    public ResponseEntity<List<TentResponse>> getPersonById(
        @RequestBody 
    )

    





}
