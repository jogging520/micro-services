package com.northbrain.family.service;

import com.northbrain.family.model.Constants;
import com.northbrain.family.model.Family;
import com.northbrain.family.model.FamilyHistory;
import com.northbrain.family.repository.IFamilyHistoryRepository;
import com.northbrain.family.repository.IFamilyRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
@Log
public class FamilyService {
    private final IFamilyRepository familyRepository;
    private final IFamilyHistoryRepository familyHistoryRepository;

    public FamilyService(IFamilyRepository familyRepository, IFamilyHistoryRepository familyHistoryRepository) {
        this.familyRepository = familyRepository;
        this.familyHistoryRepository = familyHistoryRepository;
    }

    /**
     * 方法：新建家庭信息
     * @param serialNo 操作流水号
     * @param families 家庭数组
     * @return 创建成功的家庭列表
     */
    public Flux<Family> createFamilies(String serialNo,
                                       Flux<Family> families) {
        return families
                .map(family -> family.
                        setStatus(Constants.FAMILY_STATUS_ACTIVE)
                        .setCreateTime(new Date())
                        .setTimestamp(new Date())
                        .setSerialNo(serialNo))
                .flatMap(family -> {
                    log.info(Constants.FAMILY_OPERATION_SERIAL_NO + serialNo);
                    log.info(family.toString());

                    this.familyHistoryRepository
                            .save(FamilyHistory.builder()
                                    .operationType(Constants.FAMILY_HISTORY_CREATE)
                                    .familyId(family.getId())
                                    .houseHolder(family.getHouseHolder())
                                    .region(family.getRegion())
                                    .masterIdCardNo(family.getMasterIdCardNo())
                                    .phone(family.getPhone())
                                    .createTime(family.getCreateTime())
                                    .timestamp(new Date())
                                    .status(family.getStatus())
                                    .serialNo(serialNo)
                                    .description(family.getDescription())
                                    .build());

                    return this.familyRepository
                            .save(family);
                });
    }

    /**
     * 方法：按照ID号查询家庭信息
     * @param serialNo 操作流水号
     * @param familyId 家庭编号
     * @return 家庭信息
     */
    public Mono<Family> queryFamilyById(String serialNo,
                                        String familyId) {
        return this.familyRepository
                .findById(familyId)
                .map(family -> {
                    log.info(Constants.FAMILY_OPERATION_SERIAL_NO + serialNo);
                    log.info(family.toString());

                    return family;
                });
    }
}
