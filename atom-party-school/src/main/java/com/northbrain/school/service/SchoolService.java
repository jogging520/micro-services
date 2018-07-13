package com.northbrain.school.service;

import com.northbrain.school.model.Constants;
import com.northbrain.school.model.School;
import com.northbrain.school.model.SchoolHistory;
import com.northbrain.school.repository.ISchoolHistoryRepository;
import com.northbrain.school.repository.ISchoolRepository;
import com.northbrain.util.timer.Clock;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Log
public class SchoolService {
    private final ISchoolRepository schoolRepository;
    private final ISchoolHistoryRepository schoolHistoryRepository;

    public SchoolService(ISchoolRepository schoolRepository, ISchoolHistoryRepository schoolHistoryRepository) {
        this.schoolRepository = schoolRepository;
        this.schoolHistoryRepository = schoolHistoryRepository;
    }

    /**
     * 方法：创建学校
     * @param serialNo 操作流水号
     * @param schools 学校列表
     * @return 创建成功的学校列表
     */
    public Flux<School> createSchools(String serialNo,
                                      Flux<School> schools) {
        return schools
                .flatMap(school -> this.schoolRepository
                        .findByName(school.getName())
                        .map(newSchool -> newSchool.setStatus(Constants.SCHOOL_ERRORCODE_HAS_EXISTS))
                        .switchIfEmpty(this.schoolRepository
                                .save(school
                                        .setStatus(Constants.SCHOOL_STATUS_ACTIVE)
                                        .setCreateTime(Clock.currentTime())
                                        .setTimestamp(Clock.currentTime())
                                        .setSerialNo(serialNo))
                                .map(newSchool -> {
                                    log.info(Constants.SCHOOL_OPERATION_SERIAL_NO + serialNo);
                                    log.info(newSchool.toString());

                                    this.schoolHistoryRepository
                                            .save(SchoolHistory.builder()
                                                    .operationType(Constants.SCHOOL_HISTORY_CREATE)
                                                    .schoolId(newSchool.getId())
                                                    .type(newSchool.getType())
                                                    .name(newSchool.getName())
                                                    .region(newSchool.getRegion())
                                                    .masterName(newSchool.getMasterName())
                                                    .avatar(newSchool.getAvatar())
                                                    .phone(newSchool.getPhone())
                                                    .createTime(newSchool.getCreateTime())
                                                    .timestamp(Clock.currentTime())
                                                    .status(newSchool.getStatus())
                                                    .serialNo(serialNo)
                                                    .description(newSchool.getDescription())
                                                    .build())
                                            .subscribe(schoolHistory -> {
                                                log.info(Constants.SCHOOL_OPERATION_SERIAL_NO + serialNo);
                                                log.info(schoolHistory.toString());
                                            });

                                    return newSchool.setStatus(Constants.SCHOOL_ERRORCODE_SUCCESS);
                                })
                        ));
    }

    /**
     * 方法：根据区域查询学校
     * @param serialNo 操作流水号
     * @param regions 区域数组
     * @return 学校列表
     */
    public Flux<School> querySchoolsByRegions(String serialNo,
                                              String[] regions) {
        return this.schoolRepository
                .findByRegionIn(regions)
                .map(school -> {
                    log.info(Constants.SCHOOL_OPERATION_SERIAL_NO + serialNo);
                    log.info(school.toString());

                    return school;
                });
    }

    /**
     * 方法：按照名称模糊匹配学校
     * @param serialNo 操作流水号
     * @param name 学校名称
     * @return 学校列表
     */
    public Flux<School> querySchoolsByName(String serialNo,
                                           String name) {
        return this.schoolRepository
                .findByNameLike(name)
                .map(school -> {
                    log.info(Constants.SCHOOL_OPERATION_SERIAL_NO + serialNo);
                    log.info(school.toString());

                    return school;
                });
    }

    /**
     * 方法：按照学校ID查找学校信息
     * @param serialNo 操作流水号
     * @param schoolId 学校ID
     * @return 学校信息
     */
    public Mono<School> querySchoolById(String serialNo,
                                        String schoolId) {
        return this.schoolRepository
                .findById(schoolId)
                .map(school -> {
                    log.info(Constants.SCHOOL_OPERATION_SERIAL_NO + serialNo);
                    log.info(school.toString());

                    return school;
                });
    }
}
