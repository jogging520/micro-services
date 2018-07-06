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

import java.util.Calendar;
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
     * 根据户主和电话进行过滤，如果已经存在，那么直接报错。
     * @param serialNo 操作流水号
     * @param families 家庭数组
     * @return 创建成功的家庭列表
     */
    public Flux<Family> createFamilies(String serialNo,
                                       Flux<Family> families) {
        return families
                .flatMap(family -> this.familyRepository
                        .findByHouseHolderAndPhone(family.getHouseHolder(), family.getPhone())
                        .map(newFamily -> newFamily.setStatus(Constants.FAMILY_ERRORCODE_HAS_EXISTS))
                        .switchIfEmpty(this.familyRepository
                                .save(family
                                        .setStatus(Constants.FAMILY_STATUS_ACTIVE)
                                        .setCreateTime(new Date())
                                        .setTimestamp(new Date())
                                        .setSerialNo(serialNo))
                                .map(newFamily -> {
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
                                                    .build())
                                            .subscribe(familyHistory -> {
                                                log.info(Constants.FAMILY_OPERATION_SERIAL_NO + serialNo);
                                                log.info(familyHistory.toString());
                                            });

                                    return newFamily.setStatus(Constants.FAMILY_ERRORCODE_SUCCESS);
                                })
                        ));
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
