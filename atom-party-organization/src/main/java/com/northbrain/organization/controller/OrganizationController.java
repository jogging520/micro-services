package com.northbrain.organization.controller;

import com.northbrain.organization.model.Constants;
import com.northbrain.organization.model.Organization;
import com.northbrain.organization.model.Region;
import com.northbrain.organization.service.OrganizationService;
import com.sun.org.apache.regexp.internal.RE;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class OrganizationController {
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * 方法：查找区域信息
     * @param serialNo 流水号
     * @return 区域信息
     */
    @GetMapping(Constants.ORGANIZATION_REGION_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Region>> queryRegions(@RequestParam String serialNo) {
        return ResponseEntity
                .ok()
                .body(this.organizationService
                        .queryRegions(serialNo));
    }

    /**
     * 方法：查询组织机构信息
     * @param serialNo 流水号
     * @return 组织机构信息
     */
    @GetMapping(Constants.ORGANIZATION_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Organization>> queryOrganizations(@RequestParam String serialNo) {
        return ResponseEntity
                .ok()
                .body(this.organizationService
                        .queryOrganizations(serialNo));
    }

    /**
     * 方法：创建区域信息
     * @param serialNo 流水号
     * @param region 区域对象
     * @return 创建成功的区域信息
     */
    @PostMapping(Constants.ORGANIZATION_REGION_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Region>> createRegion(@RequestParam String serialNo,
                                                     @RequestBody Region region) {
        return ResponseEntity
                .ok()
                .body(this.organizationService
                        .createRegion(serialNo, region));
    }

    /**
     * 创建组织机构
     * @param serialNo 流水号
     * @param organization 组织机构
     * @return 创建成功的组织机构
     */
    @PostMapping(Constants.ORGANIZATION_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Organization>> createOrganization(String serialNo,
                                                                 Organization organization) {
        return ResponseEntity
                .ok()
                .body(this.organizationService
                        .createOrganization(serialNo, organization));
    }
}
