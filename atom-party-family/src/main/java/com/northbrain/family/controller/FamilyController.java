package com.northbrain.family.controller;

import com.northbrain.family.model.Constants;
import com.northbrain.family.model.Family;
import com.northbrain.family.service.FamilyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FamilyController {
    private final FamilyService familyService;

    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    @GetMapping(Constants.FAMILY_SPECIFIED_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Family>> queryFamily(@RequestParam String operationId,
                                                    @PathVariable String familyId) {
        return ResponseEntity.ok()
                .body(this.familyService
                        .queryFamilyById(operationId, familyId));
    }
}
