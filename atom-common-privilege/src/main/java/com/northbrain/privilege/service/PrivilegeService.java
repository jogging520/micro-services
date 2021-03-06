package com.northbrain.privilege.service;

import com.northbrain.privilege.model.Constants;
import com.northbrain.privilege.model.Permission;
import com.northbrain.privilege.model.Role;
import com.northbrain.privilege.model.RoleHistory;
import com.northbrain.privilege.repository.IPermissionRepository;
import com.northbrain.privilege.repository.IRoleHistoryRepository;
import com.northbrain.privilege.repository.IRoleRepository;
import com.northbrain.util.timer.Clock;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Log
public class PrivilegeService {
    private final IRoleRepository roleRepository;
    private final IPermissionRepository permissionRepository;
    private final IRoleHistoryRepository roleHistoryRepository;

    public PrivilegeService(IRoleRepository roleRepository, IPermissionRepository permissionRepository,
                            IRoleHistoryRepository roleHistoryRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.roleHistoryRepository = roleHistoryRepository;
    }

    /**
     * 方法：根据应用类型查找角色清单
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param roles 角色
     * @return 权限实体数组
     */
    public Flux<Role> queryRolesByAppType(String serialNo,
                                          String appType,
                                          String category,
                                          String[] roles) {
        return this.roleRepository
                .findByIdInAndAppTypesContainingAndCategory(roles, appType, category)
                .map(role -> {
                    log.info(Constants.PRIVILEGE_OPERATION_SERIAL_NO + serialNo);
                    log.info(role.toString());
                    return role;
                });
    }

    /**
     * 方法：创建角色
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @param role 角色
     * @return 创建成功的角色
     */
    public Mono<Role> createRole(String serialNo,
                                 String category,
                                 Role role) {
        return this.roleRepository
                .findById(role.getId())
                .map(newRole -> newRole.setStatus(Constants.PRIVILEGE_ERRORCODE_HAS_EXISTS))
                .switchIfEmpty(
                        this.roleRepository
                                .save(role
                                        .setCategory(category)
                                        .setStatus(Constants.PRIVILEGE_STATUS_ACTIVE)
                                        .setCreateTime(Clock.currentTime())
                                        .setTimestamp(Clock.currentTime())
                                        .setSerialNo(serialNo))
                                .map(newRole -> {
                                    log.info(Constants.PRIVILEGE_OPERATION_SERIAL_NO + serialNo);
                                    log.info(role.toString());

                                    this.roleHistoryRepository
                                            .save(RoleHistory.builder()
                                                    .operationType(Constants.PRIVILEGE_HISTORY_CREATE)
                                                    .roleId(newRole.getId())
                                                    .type(newRole.getType())
                                                    .name(newRole.getName())
                                                    .appTypes(newRole.getAppTypes())
                                                    .category(newRole.getCategory())
                                                    .permissions(newRole.getPermissions())
                                                    .createTime(newRole.getCreateTime())
                                                    .timestamp(Clock.currentTime())
                                                    .status(newRole.getStatus())
                                                    .serialNo(serialNo)
                                                    .description(newRole.getDescription())
                                                    .build());

                                    return newRole.setStatus(Constants.PRIVILEGE_ERRORCODE_SUCCESS);
                                }));
    }

    /**
     * 方法：按照ID号查询权限实体信息
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @param permissions 权限实体编号数组
     * @return 权限清单
     */
    public Flux<Permission> queryPermissionsByIds(String serialNo,
                                                  String category,
                                                  String[] permissions) {
        return this.permissionRepository
                .findByIdInAndCategory(permissions, category)
                .map(permission -> {
                    log.info(Constants.PRIVILEGE_OPERATION_SERIAL_NO + serialNo);
                    log.info(permission.toString());
                    return permission;
                });
    }
}
