package com.northbrain.demo.service;

import com.northbrain.demo.model.Constants;
import com.northbrain.demo.model.Girl;
import com.northbrain.demo.repository.IGirlRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DemoService {
    private final IGirlRepository girlRepository;

    public DemoService(IGirlRepository girlRepository) {
        this.girlRepository = girlRepository;
    }

    public Mono<Girl> createNewGirl(String firstName,
                                    String lastName,
                                    String mobiles[]) {
        return this.girlRepository
                .save(
                        Girl.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .mobiles(mobiles)
                        .status(Constants.DEMO_STATUS_ACTIVE)
                        .build()
                );
    }

    public Flux<Girl> listing() {
        return this.girlRepository
                .findByStatus(Constants.DEMO_STATUS_ACTIVE);
    }
}
