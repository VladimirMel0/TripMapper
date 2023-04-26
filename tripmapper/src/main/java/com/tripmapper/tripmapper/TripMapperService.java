package com.tripmapper.tripmapper;

import reactor.core.publisher.Mono;

public interface TripMapperService {
    Mono<String> createTripMapper (String destiny);
}
