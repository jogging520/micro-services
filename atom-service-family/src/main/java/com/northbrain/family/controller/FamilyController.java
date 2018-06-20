package com.northbrain.family.controller;

import com.northbrain.family.model.Constants;
import com.northbrain.family.model.Family;
import com.northbrain.family.service.FamilyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class FamilyController {
    public final FamilyService familyService;

    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    @PostMapping(Constants.FAMILY_HTTP_REQUEST_MAPPING)
    public Flux<Family> createFamilies(@RequestBody Flux<Family> families) {
        return this.familyService
                .createFamlies(families);
    }

    @GetMapping(Constants.FAMILY_HTTP_REQUEST_MAPPING)
    public Flux<Family> queryFamilies() {
        return this.familyService
                .queryFamilies();
    }
}
