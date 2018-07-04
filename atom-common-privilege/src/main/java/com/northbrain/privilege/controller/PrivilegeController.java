package com.northbrain.privilege.controller;

import com.northbrain.privilege.model.Constants;
import com.northbrain.privilege.model.Permission;
import com.northbrain.privilege.model.Role;
import com.northbrain.privilege.service.PrivilegeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PrivilegeController {
    private final PrivilegeService privilegeService;

    public PrivilegeController(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }

    /**
     * 方法：根据应用类型查找角色清单
     * @param serialNo 流水号
     * @param roles 角色
     * @param appType 应用类型
     * @return 权限实体数组
     */
    @GetMapping(Constants.PRIVILEGE_ROLE_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Role>> queryRolesByAppType(@RequestParam String serialNo,
                                                          @RequestParam String[] roles,
                                                          @RequestParam String appType) {
        return ResponseEntity
                .ok()
                .body(this.privilegeService
                        .queryRolesByAppType(serialNo, roles, appType));
    }

    /**
     * 方法：创建角色
     * @param serialNo 流水号
     * @param role 角色
     * @return 创建成功的角色
     */
    @PostMapping(Constants.PRIVILEGE_ROLE_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Role>> createRole(@RequestParam String serialNo,
                                                 @RequestBody Role role) {
        return ResponseEntity.ok()
                .body(this.privilegeService
                        .createRole(serialNo, role));
    }

    /**
     * 方法：按照ID号查询权限实体信息
     * @param serialNo 流水号
     * @param permissions 权限实体编号数组
     * @return 权限清单
     */
    @GetMapping(Constants.PRIVILEGE_PERMISSION_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Permission>> queryPermissionsByIds(@RequestParam String serialNo,
                                                                  @RequestParam String[] permissions) {
        return ResponseEntity.ok()
                .body(this.privilegeService
                        .queryPermissionsByIds(serialNo, permissions));
    }
}
