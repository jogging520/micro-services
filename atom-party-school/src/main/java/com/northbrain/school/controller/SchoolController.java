package com.northbrain.school.controller;

import com.northbrain.school.model.Constants;
import com.northbrain.school.model.School;
import com.northbrain.school.service.SchoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    /**
     * 方法：创建学校
     * @param serialNo 操作流水号
     * @param schools 学校列表
     * @return 创建成功的学校列表
     */
    @PostMapping(Constants.SCHOOL_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<School>> createSchools(@RequestParam String serialNo,
                                                      @RequestBody Flux<School> schools) {
        return ResponseEntity.ok()
                .body(this.schoolService
                        .createSchools(serialNo, schools));
    }

    /**
     * 方法：根据区域查询学校
     * @param serialNo 操作流水号
     * @param regions 区域数组
     * @return 学校列表
     */
    @GetMapping(Constants.SCHOOL_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<School>> querySchoolsByRegions(@RequestParam String serialNo,
                                                              @RequestParam String[] regions) {
        return ResponseEntity.ok()
                .body(this.schoolService
                        .querySchoolsByRegions(serialNo, regions));
    }

    /**
     * 方法：按照名称模糊匹配学校
     * @param serialNo 操作流水号
     * @param name 学校名称
     * @return 学校列表
     */
    @GetMapping(Constants.SCHOOL_SPECIFIED_NAME_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<School>> querySchoolsByName(@RequestParam String serialNo,
                                                           @RequestParam String name) {
        return ResponseEntity.ok()
                .body(this.schoolService
                        .querySchoolsByName(serialNo, name));
    }

    /**
     * 方法：按照学校ID查找学校信息
     * @param serialNo 操作流水号
     * @param schoolId 学校ID
     * @return 学校信息
     */
    @GetMapping(Constants.SCHOOL_SPECIFIED_ID_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<School>> querySchoolById(@RequestParam String serialNo,
                                                        @RequestParam String schoolId) {
        return ResponseEntity.ok()
                .body(this.schoolService
                        .querySchoolById(serialNo, schoolId));
    }
}
