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

    public Flux<Student> queryStudents() {
        return this.studentRepository
                .findAll();
    }

    public Flux<Student> createStudents(Flux<Student> students) {
        return students
                .flatMap(student -> this.studentRepository
                            .save(student)
                );
    }
}
