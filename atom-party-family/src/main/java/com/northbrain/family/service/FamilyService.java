package com.northbrain.family.service;

import com.northbrain.family.model.Family;
import com.northbrain.family.repository.IFamilyRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FamilyService {
    private final IFamilyRepository familyRepository;

    public FamilyService(IFamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    public Mono<Family> queryFamily(String familyId) {
        return this.familyRepository
                .findById(familyId);
    }
}
