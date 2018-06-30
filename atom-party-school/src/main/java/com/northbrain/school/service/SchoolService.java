package com.northbrain.school.service;

import com.northbrain.school.model.School;
import com.northbrain.school.repository.ISchoolRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SchoolService {
    private final ISchoolRepository schoolRepository;

    public SchoolService(ISchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public Flux<School> createSchools(String operationId,
                                      Flux<School> schools) {
        return this.schoolRepository
                .saveAll(schools)
                .log(operationId);
    }

    public Flux<School> querySchoolsByRegionIds(String operationId,
                                                String[] regionIds) {
        return this.schoolRepository
                .findByRegionIdIn(regionIds)
                .log(operationId);
    }

    public Flux<School> querySchoolsByName(String operationId,
                                           String name) {
        return this.schoolRepository
                .findByNameLike(name)
                .log(operationId);
    }

    public Mono<School> querySchoolById(String operationId,
                                        String schoolId) {
        return this.schoolRepository
                .findById(schoolId)
                .log(operationId);
    }
}
