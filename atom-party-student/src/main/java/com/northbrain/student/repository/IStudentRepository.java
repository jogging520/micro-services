package com.northbrain.student.repository;

import com.northbrain.student.model.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IStudentRepository extends ReactiveCrudRepository<Student, String> {
    Flux<Student> findByRegionIdIn(String[] regionIds);
}
