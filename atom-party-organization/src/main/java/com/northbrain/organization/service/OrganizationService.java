package com.northbrain.organization.service;

import com.northbrain.organization.model.Organization;
import com.northbrain.organization.model.Region;
import com.northbrain.organization.repository.IOrganizationRepository;
import com.northbrain.organization.repository.IRegionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class OrganizationService {
    private final IRegionRepository regionRepository;
    private final IOrganizationRepository organizationRepository;

    public OrganizationService(IRegionRepository regionRepository, IOrganizationRepository organizationRepository) {
        this.regionRepository = regionRepository;
        this.organizationRepository = organizationRepository;
    }

    public Flux<Region> queryRegions() {
        return this.regionRepository
                        .findAll();
    }

    public Flux<Organization> queryOrganizations() {
        return this.organizationRepository
                        .findAll();
    }
}
