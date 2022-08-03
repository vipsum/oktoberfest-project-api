package net.oktoberfest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.oktoberfest.model.client.request.TentRequest;
import net.oktoberfest.model.client.response.TentResponse;
import net.oktoberfest.services.TentService;

@RestController
@RequestMapping("/RegisterTent")
@AllArgsConstructor
public class TentController {

    private final TentService tentService;

    @PostMapping
    public ResponseEntity<TentResponse> createTent(
            @RequestBody TentRequest tentRequest) {

        return new ResponseEntity<>(
                this.tentService.createTent(tentRequest.construct()).response(),
                HttpStatus.OK);
    }
}
