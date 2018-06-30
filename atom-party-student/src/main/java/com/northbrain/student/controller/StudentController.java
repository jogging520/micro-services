package com.northbrain.student.controller;

import com.northbrain.student.model.Constants;
import com.northbrain.student.model.Student;
import com.northbrain.student.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(Constants.STUDENT_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Student>> queryStudentsByRegionIds(@RequestParam String operationId,
                                                                  @RequestParam String[] regionIds) {
        return ResponseEntity.ok()
                .body(this.studentService
                        .queryStudentsByRegionIds(operationId, regionIds));
    }

    @PostMapping(Constants.STUDENT_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Student>> createStudents(@RequestParam String operationId,
                                                        @RequestBody  Flux<Student> students) {
        return ResponseEntity.ok()
                .body(this.studentService
                        .createStudents(operationId, students));
    }
}
