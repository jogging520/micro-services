package com.northbrain.student.service;

import com.northbrain.student.model.Student;
import com.northbrain.student.repository.IStudentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class StudentService {
    private final IStudentRepository studentRepository;

    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Flux<Student> queryStudentsByRegionIds(String operationId,
                                                  String[] regionIds) {
        return this.studentRepository
                .findByRegionIdIn(regionIds)
                .log(operationId);
    }

    public Flux<Student> createStudents(String operationId,
                                        Flux<Student> students) {
        return this.studentRepository
                .saveAll(students)
                .log(operationId);
    }
}
