package com.northbrain.family.controller;

import com.northbrain.family.model.Constants;
import com.northbrain.family.model.Family;
import com.northbrain.family.service.FamilyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class FamilyController {
    private final FamilyService familyService;

    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    /**
     * 方法：新建家庭信息
     * @param serialNo 操作流水号
     * @param category 类别（企业）
     * @param families 家庭数组
     * @return 创建成功的家庭列表
     */
    @PostMapping(Constants.FAMILY_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Family>> createFamilies(@RequestParam String serialNo,
                                                       @RequestParam String category,
                                                       @RequestBody Flux<Family> families) {
        return ResponseEntity.ok()
                .body(this.familyService
                        .createFamilies(serialNo, category, families));
    }

    /**
     * 方法：按照ID号查询家庭信息
     * @param serialNo 操作流水号
     * @param category 类别（企业）
     * @param familyId 家庭编号
     * @return 家庭信息
     */
    @GetMapping(Constants.FAMILY_SPECIFIED_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Family>> queryFamilyById(@RequestParam String serialNo,
                                                        @RequestParam String category,
                                                        @PathVariable String familyId) {
        return ResponseEntity.ok()
                .body(this.familyService
                        .queryFamilyById(serialNo, category, familyId));
    }
}
