package com.northbrain.student.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.northbrain.student.model.StudentHistory;

public interface IStudentHistoryRepository extends ReactiveCrudRepository<StudentHistory, String> {
}
