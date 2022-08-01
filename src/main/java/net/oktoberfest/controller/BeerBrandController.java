package net.oktoberfest.controller;

import net.oktoberfest.model.entities.BeerBrand;
import net.oktoberfest.model.client.request.BeerBrandRequest;
import net.oktoberfest.model.client.response.BeerBrandResponse;
import net.oktoberfest.services.BeerBrandService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("BeerBrand")
@AllArgsConstructor

public class BeerBrandController {
    private final BeerBrandService beerBrandService;

    @PostMapping
    public ResponseEntity<BeerBrandResponse> createBeerBrand(
            @RequestBody BeerBrandRequest beerBrandRequest) {

            return new ResponseEntity<>(
                this.beerBrandService.createBeerBrand(beerBrandRequest.construct(),
                HttpStatus.OK));
    }

}