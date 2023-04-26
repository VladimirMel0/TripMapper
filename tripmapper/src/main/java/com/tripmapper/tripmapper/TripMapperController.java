package com.tripmapper.tripmapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class TripMapperController {
    @Autowired
    private ServicesTripMapper service;

    @PostMapping("/trip-mapper")
    public Mono<String> createTripMapper(@RequestBody String destiny){
        return service.createTripMapper(destiny).map(response -> response.choices().get(0).text());
    }
}
