package com.northbrain.privilege.service;

import com.northbrain.privilege.model.Role;
import com.northbrain.privilege.repository.IPermissionRepository;
import com.northbrain.privilege.repository.IRoleRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class PrivilegeService {
    private final IRoleRepository roleRepository;
    private final IPermissionRepository permissionRepository;

    public PrivilegeService(IRoleRepository roleRepository, IPermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    public Flux<Role> queryPermissions(String[] roleIds,
                                       String appType) {
        return this.roleRepository
                .findByIdInAndAppTypesContaining(roleIds, appType)
                .log();
    }
}
