package com.northbrain.school.controller;

import com.northbrain.school.model.Constants;
import com.northbrain.school.model.School;
import com.northbrain.school.service.SchoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping(Constants.SCHOOL_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<School>> createSchools(@RequestParam String operationId,
                                                      @RequestBody Flux<School> schools) {
        return ResponseEntity.ok()
                .body(this.schoolService
                        .createSchools(operationId, schools));
    }

    @GetMapping(Constants.SCHOOL_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<School>> querySchoolsByRegionIds(@RequestParam String operationId,
                                                                @RequestParam String[] regionIds) {
        return ResponseEntity.ok()
                .body(this.schoolService
                        .querySchoolsByRegionIds(operationId, regionIds));
    }

    @GetMapping(Constants.SCHOOL_SPECIFIED_NAME_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<School>> querySchoolsByName(@RequestParam String operationId,
                                                           @RequestParam String name) {
        return ResponseEntity.ok()
                .body(this.schoolService
                        .querySchoolsByName(operationId, name));
    }

    @GetMapping(Constants.SCHOOL_SPECIFIED_ID_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<School>> querySchoolsById(@RequestParam String operationId,
                                                         @RequestParam String schoolId) {
        return ResponseEntity.ok()
                .body(this.schoolService
                        .querySchoolById(operationId, schoolId));
    }
}
