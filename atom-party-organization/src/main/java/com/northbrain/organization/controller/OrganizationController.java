package com.northbrain.organization.controller;

import com.northbrain.organization.model.Constants;
import com.northbrain.organization.model.Organization;
import com.northbrain.organization.model.Region;
import com.northbrain.organization.service.OrganizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
}
