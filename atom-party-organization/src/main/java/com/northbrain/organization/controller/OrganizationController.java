package com.northbrain.organization.controller;

import com.northbrain.organization.model.Constants;
import com.northbrain.organization.model.Organization;
import com.northbrain.organization.model.Region;
import com.northbrain.organization.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class OrganizationController {
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping(Constants.ORGANIZATION_REGION_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Region>> queryRegions() {
        return ResponseEntity
                .ok()
                .body(this.organizationService
                        .queryRegions());
    }

    @GetMapping(Constants.ORGANIZATION_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Organization>> queryOrganizations() {
        return ResponseEntity
                .ok()
                .body(this.organizationService
                        .queryOrganizations());
    }

    @GetMapping(Constants.ORGANIZATION_HTTP_REQUEST_MAPPING_TEST)
    public ResponseEntity<Mono<Organization>> test() {
        return ResponseEntity
                .badRequest()
                .body(Mono.just(Organization
                        .builder()
                        .name("test name")
                        .build()));
        //return ResponseEntity.ok().body(Mono.error(new RuntimeException("{key: 'dafd', value: 'jdfa'}")));
    }
}
