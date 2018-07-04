package com.northbrain.student.repository;

import com.northbrain.student.model.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IStudentRepository extends ReactiveCrudRepository<Student, String> {
    Flux<Student> findByStatusAndRegionIn(String status, String[] regions);

    Flux<Student> findByStatusAndFamily(String status, String family);

    Flux<Student> findByStatusAndSchool(String status, String school);
}
