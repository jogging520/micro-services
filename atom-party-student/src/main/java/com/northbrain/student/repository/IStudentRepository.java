package com.northbrain.student.repository;

import com.northbrain.student.model.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IStudentRepository extends ReactiveCrudRepository<Student, String> {
}
