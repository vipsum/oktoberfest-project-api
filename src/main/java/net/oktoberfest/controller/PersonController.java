package net.oktoberfest.controller;

import lombok.AllArgsConstructor;
import net.oktoberfest.model.client.request.PersonRequest;
import net.oktoberfest.model.client.response.PersonResponse;
import net.oktoberfest.model.entities.BeerBrand;
import net.oktoberfest.services.BeerBrandService;
import net.oktoberfest.services.PersonService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {
    private final PersonService personService;
    private final BeerBrandService beerBrandService;

    @PostMapping
    public ResponseEntity<PersonResponse> createPerson(
            @RequestBody PersonRequest personRequest) {

            List<BeerBrand> beerBrandList =  beerBrandService.getBeerBrandsFromIdList(personRequest.getPreferredBeerBrand()
                );

        return new ResponseEntity<>(
                this.personService.createPerson(personRequest.construct(beerBrandList)).response(),
                HttpStatus.OK);
    }

}
