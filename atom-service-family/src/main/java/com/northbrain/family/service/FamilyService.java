package com.northbrain.family.service;

import com.northbrain.family.model.Family;
import com.northbrain.family.repository.IFamilyRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class FamilyService {
    public final IFamilyRepository familyRepository;

    public FamilyService(IFamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    public Flux<Family> createFamlies(Flux<Family> families) {
        return this.familyRepository
                .saveAll(families);
    }

    public Flux<Family> queryFamilies() {
        return this.familyRepository
                .findAll();
    }
}
