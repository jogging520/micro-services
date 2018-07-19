package com.northbrain.student.controller;

import com.northbrain.student.model.Constants;
import com.northbrain.student.model.Student;
import com.northbrain.student.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 方法：根据区域查询学生信息
     * @param serialNo serialNo 流水号
     * @param category 类别（企业）
     * @param regions 区域数组
     * @param family 家庭信息
     * @param school 学校信息
     * @return 用户信息列表
     */
    @GetMapping(Constants.STUDENT_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Student>> queryStudents(@RequestParam String serialNo,
                                                       @RequestParam String category,
                                                       @RequestParam(required = false) String[] regions,
                                                       @RequestParam(required = false) String family,
                                                       @RequestParam(required = false) String school) {
        if(regions != null)
            return ResponseEntity.ok()
                    .body(this.studentService
                            .queryStudentsByRegions(serialNo, category, regions));

        if(family != null)
            return ResponseEntity.ok()
                    .body(this.studentService
                            .queryStudentsByFamily(serialNo, category, family));

        if(school != null)
            return ResponseEntity.ok()
                    .body(this.studentService
                            .queryStudentsBySchool(serialNo, category, school));

        return ResponseEntity.badRequest().body(null);
    }

    /**
     * 方法：根据学生编号查询学生信息
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @param studentId 学生编号
     * @return 学生信息
     */
    @GetMapping(Constants.STUDENT_SPECIFIED_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Student>> queryStudentById(@RequestParam String serialNo,
                                                          @RequestParam String category,
                                                          @PathVariable String studentId) {
        return ResponseEntity.ok()
                .body(this.studentService
                        .queryStudentById(serialNo, category, studentId));
    }

    /**
     * 方法：创建学生信息
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @param students 学生列表
     * @return 创建成功的学生列表
     */
    @PostMapping(Constants.STUDENT_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Student>> createStudents(@RequestParam String serialNo,
                                                        @RequestParam String category,
                                                        @RequestBody  Flux<Student> students) {
        return ResponseEntity.ok()
                .body(this.studentService
                        .createStudents(serialNo, category, students));
    }
}
