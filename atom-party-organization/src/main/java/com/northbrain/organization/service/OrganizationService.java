package com.northbrain.organization.service;

import com.northbrain.organization.model.*;
import com.northbrain.organization.repository.IOrganizationHistoryRepository;
import com.northbrain.organization.repository.IOrganizationRepository;
import com.northbrain.organization.repository.IRegionHistoryRepository;
import com.northbrain.organization.repository.IRegionRepository;
import com.northbrain.util.timer.Clock;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Log
public class OrganizationService {
    private final IRegionRepository regionRepository;
    private final IRegionHistoryRepository regionHistoryRepository;
    private final IOrganizationRepository organizationRepository;
    private final IOrganizationHistoryRepository organizationHistoryRepository;

    public OrganizationService(IRegionRepository regionRepository,
                               IRegionHistoryRepository regionHistoryRepository,
                               IOrganizationRepository organizationRepository,
                               IOrganizationHistoryRepository organizationHistoryRepository) {
        this.regionRepository = regionRepository;
        this.regionHistoryRepository = regionHistoryRepository;
        this.organizationRepository = organizationRepository;
        this.organizationHistoryRepository = organizationHistoryRepository;
    }

    /**
     * 方法：查找区域信息
     * @param serialNo 流水号
     * @return 区域信息
     */
    public Flux<Region> queryRegions(String serialNo) {
        return this.regionRepository
                .findAll()
                .map(region -> {
                    log.info(Constants.ORGANIZATION_OPERATION_SERIAL_NO + serialNo);
                    log.info(region.toString());
                    return region;
                });
    }

    /**
     * 方法：查询组织机构信息
     * @param serialNo 流水号
     * @return 组织机构信息
     */
    public Flux<Organization> queryOrganizations(String serialNo) {
        return this.organizationRepository
                .findAll()
                .map(organization -> {
                    log.info(Constants.ORGANIZATION_OPERATION_SERIAL_NO + serialNo);
                    log.info(organization.toString());
                    return organization;
                });
    }

    /**
     * 创建组织机构
     * @param serialNo 流水号
     * @param organization 组织机构
     * @return 创建成功的组织机构
     */
    public Mono<Organization> createOrganization(String serialNo,
                                                 Organization organization) {
        return this.organizationRepository
                .save(organization
                        .setStatus(Constants.ORGANIZATION_STATUS_ACTIVE)
                        .setCreateTime(Clock.currentTime())
                        .setTimestamp(Clock.currentTime())
                        .setSerialNo(serialNo))
                .map(newOrganization -> {
                    log.info(Constants.ORGANIZATION_OPERATION_SERIAL_NO + serialNo);
                    log.info(newOrganization.toString());

                    this.organizationHistoryRepository
                            .save(OrganizationHistory.builder()
                                    .operationType(Constants.ORGANIZATION_HISTORY_CREATE)
                                    .organizationId(newOrganization.getId())
                                    .code(newOrganization.getCode())
                                    .name(newOrganization.getName())
                                    .region(newOrganization.getRegion())
                                    .parent(newOrganization.getParent())
                                    .createTime(newOrganization.getCreateTime())
                                    .timestamp(Clock.currentTime())
                                    .status(newOrganization.getStatus())
                                    .serialNo(serialNo)
                                    .description(newOrganization.getDescription())
                                    .children(newOrganization.getChildren())
                                    .build())
                            .subscribe(organizationHistory -> {
                                log.info(Constants.ORGANIZATION_OPERATION_SERIAL_NO + serialNo);
                                log.info(organizationHistory.toString());
                            });

                    return newOrganization.setStatus(Constants.ORGANIZATION_ERRORCODE_SUCCESS);
                });
    }

    /**
     * 方法：创建区域信息
     * @param serialNo 流水号
     * @param region 区域对象
     * @return 创建成功的区域信息
     */
    public Mono<Region> createRegion(String serialNo,
                                     Region region) {
        return this.regionRepository
                .save(region
                        .setStatus(Constants.ORGANIZATION_STATUS_ACTIVE)
                        .setCreateTime(Clock.currentTime())
                        .setTimestamp(Clock.currentTime())
                        .setSerialNo(serialNo))
                .map(newRegion -> {
                    log.info(Constants.ORGANIZATION_OPERATION_SERIAL_NO + serialNo);
                    log.info(newRegion.toString());

                    this.regionHistoryRepository
                            .save(RegionHistory.builder()
                                    .operationType(Constants.ORGANIZATION_HISTORY_CREATE)
                                    .regionId(newRegion.getId())
                                    .code(newRegion.getCode())
                                    .name(newRegion.getName())
                                    .level(newRegion.getLevel())
                                    .longitude(newRegion.getLongitude())
                                    .latitude(newRegion.getLatitude())
                                    .createTime(newRegion.getCreateTime())
                                    .timestamp(Clock.currentTime())
                                    .status(newRegion.getStatus())
                                    .serialNo(serialNo)
                                    .description(newRegion.getDescription())
                                    .build())
                            .subscribe(regionHistory -> {
                                log.info(Constants.ORGANIZATION_OPERATION_SERIAL_NO + serialNo);
                                log.info(regionHistory.toString());
                            });

                    return newRegion.setStatus(Constants.ORGANIZATION_ERRORCODE_SUCCESS);
                });
    }
}
