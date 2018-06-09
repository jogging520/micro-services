package com.northbrain.organization.controller;

import com.northbrain.organization.model.Constants;
import com.northbrain.organization.model.Region;
import com.northbrain.organization.service.OrganizationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class OrganizationController {
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping(Constants.ORGANIZATION_REGION_HTTP_REQUEST_MAPPING)
    public Flux<Region> queryRegions() {
        return this.organizationService
                .queryRegions();
    }
}
