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
import net.oktoberfest.services.TentService;
import net.oktoberfest.model.entities.BeerJug;
import net.oktoberfest.services.BeerJugService;

@RestController
@RequestMapping("/tents")
@AllArgsConstructor
public class TentController {

    private final TentService tentService;
    private final BeerJugService beerJugService;

    @PostMapping("/create")
    public ResponseEntity<TentResponse> createTentAndBeerJug(
            @RequestBody TentRequest tentRequest) {

        BeerJug beerJug = beerJugService.createBeerJug(tentRequest.getBeerJug());

        return new ResponseEntity<>(
                this.tentService.createTentAndBeerJug(tentRequest.construct(), beerJug).response(),
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

    @GetMapping("/show-tents-by-id/{personId}")
    public ResponseEntity<List<TentResponse>> getTentsForPersonByPreferences(
            @PathVariable Long personId) {

        return new ResponseEntity<>(
                this.tentService.getTentsForPersonByPreferences(personId)
                        .stream()
                        .map(Tent::response)
                        .collect(Collectors.toList())
                , HttpStatus.OK);
    }

    @GetMapping("/enter/{tent_id}/person/{person_id}")
    public ResponseEntity<TentResponse> getPersonById(
            @PathVariable Long tent_id, @PathVariable Long person_id) {

        return new ResponseEntity<>(
                tentService.addPersonToTent(tent_id, person_id)
                        .response(),
                HttpStatus.OK);
    }

    @GetMapping("/show-reservations-by-id/{tent_id}")
    public ResponseEntity<TentResponse> getAllReservations(
            @PathVariable Long tent_id) {

        return new ResponseEntity<>(
                tentService.getAllReservations(tent_id).response()
                , HttpStatus.OK);
    }

    @GetMapping("/reservations/create/{tent_id}/person/{person_id}")
    public ResponseEntity<TentResponse> addReservation(
            @PathVariable Long tent_id, @PathVariable Long person_id) {

            return new ResponseEntity<>(
                    this.tentService.addReservation(tent_id, person_id).response(),
                    HttpStatus.OK);
    }

    @GetMapping("/enter-game/{person_id}")
    public ResponseEntity<String> enterGame (
            @PathVariable Long person_id) {
        return new ResponseEntity<>(
                this.tentService.enterGame(person_id),
                HttpStatus.OK);
    }
}
