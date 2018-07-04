package com.northbrain.organization.service;

import com.northbrain.organization.model.Constants;
import com.northbrain.organization.model.Organization;
import com.northbrain.organization.model.Region;
import com.northbrain.organization.repository.IOrganizationRepository;
import com.northbrain.organization.repository.IRegionRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Log
public class OrganizationService {
    private final IRegionRepository regionRepository;
    private final IOrganizationRepository organizationRepository;

    public OrganizationService(IRegionRepository regionRepository, IOrganizationRepository organizationRepository) {
        this.regionRepository = regionRepository;
        this.organizationRepository = organizationRepository;
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
}
