package com.northbrain.privilege.controller;

import com.northbrain.privilege.model.Constants;
import com.northbrain.privilege.model.Role;
import com.northbrain.privilege.service.PrivilegeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class PrivilegeController {
    private final PrivilegeService privilegeService;

    public PrivilegeController(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }

    @GetMapping(Constants.PRIVILEGE_PERMISSION_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Role>> queryPermissions(@RequestParam String[] roleIds,
                                                       @RequestParam String appType) {
        return ResponseEntity
                .ok()
                .body(this.privilegeService
                        .queryPermissions(roleIds, appType));
    }
}
