package com.northbrain.student.repository;

import com.northbrain.student.model.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IStudentRepository extends ReactiveCrudRepository<Student, String> {
    Flux<Student> findByCategoryAndStatusAndRegionIn(String category, String status, String[] regions);

    Flux<Student> findByCategoryAndStatusAndFamily(String category, String status, String family);

    Flux<Student> findByCategoryAndStatusAndSchool(String category, String status, String school);

    Mono<Student> findByCategoryAndStatusAndNameAndFamily(String category, String status, String name, String family);
}
