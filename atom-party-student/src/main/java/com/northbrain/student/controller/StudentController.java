package com.northbrain.student.controller;

import com.northbrain.student.model.Constants;
import com.northbrain.student.model.Student;
import com.northbrain.student.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(Constants.STUDENT_HTTP_REQUEST_MAPPING)
    public Flux<Student> queryStudents() {
        return this.studentService
                .queryStudents();
    }

    @PostMapping(Constants.STUDENT_HTTP_REQUEST_MAPPING)
    public Flux<Student> createStudents(@RequestBody  Flux<Student> students) {
        return this.studentService
                .createStudents(students);
    }
}
