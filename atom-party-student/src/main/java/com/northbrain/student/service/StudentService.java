package com.northbrain.student.service;

import com.northbrain.student.model.Constants;
import com.northbrain.student.model.Student;
import com.northbrain.student.model.StudentHistory;
import com.northbrain.student.repository.IStudentHistoryRepository;
import com.northbrain.student.repository.IStudentRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
@Log
public class StudentService {
    private final IStudentRepository studentRepository;
    private final IStudentHistoryRepository studentHistoryRepository;

    public StudentService(IStudentRepository studentRepository, IStudentHistoryRepository studentHistoryRepository) {
        this.studentRepository = studentRepository;
        this.studentHistoryRepository = studentHistoryRepository;
    }

    /**
     * 方法：根据区域查询学生信息
     * @param serialNo 流水号
     * @param regions 区域数组
     * @return 用户信息列表
     */
    public Flux<Student> queryStudentsByRegions(String serialNo,
                                                String[] regions) {
        return this.studentRepository
                .findByStatusAndRegionIn(Constants.STUDENT_STATUS_ACTIVE, regions)
                .map(student -> {
                    log.info(Constants.STUDENT_OPERATION_SERIAL_NO + serialNo);
                    log.info(student.toString());
                    return student;
                });
    }

    /**
     * 方法：根据学生编号查询学生信息
     * @param serialNo 流水号
     * @param studentId 学生编号
     * @return 学生信息
     */
    public Mono<Student> queryStudentById(String serialNo,
                                          String studentId) {
        return this.studentRepository
                .findById(studentId)
                .map(student -> {
                    log.info(Constants.STUDENT_OPERATION_SERIAL_NO + serialNo);
                    log.info(student.toString());
                    return student;
                });
    }

    /**
     * 方法：根据家庭信息查找学生信息
     * @param serialNo 操作流水号
     * @param family 家庭编号
     * @return 学生信息
     */
    public Flux<Student> queryStudentsByFamily(String serialNo,
                                               String family) {
        return this.studentRepository
                .findByStatusAndFamily(Constants.STUDENT_STATUS_ACTIVE, family)
                .map(student -> {
                    log.info(Constants.STUDENT_OPERATION_SERIAL_NO + serialNo);
                    log.info(student.toString());
                    return student;
                });
    }

    /**
     * 方法：根据家庭信息查找学生信息
     * @param serialNo 操作流水号
     * @param school 学校编号
     * @return 学生信息
     */
    public Flux<Student> queryStudentsBySchool(String serialNo,
                                               String school) {
        return this.studentRepository
                .findByStatusAndSchool(Constants.STUDENT_STATUS_ACTIVE, school)
                .map(student -> {
                    log.info(Constants.STUDENT_OPERATION_SERIAL_NO + serialNo);
                    log.info(student.toString());
                    return student;
                });
    }

    /**
     * 方法：创建学生信息
     * @param serialNo 流水号
     * @param students 学生列表
     * @return 创建成功的学生列表
     */
    public Flux<Student> createStudents(String serialNo,
                                        Flux<Student> students) {
        return students
                .flatMap(student -> this.studentRepository
                        .save(student
                                .setStatus(Constants.STUDENT_STATUS_ACTIVE)
                                .setCreateTime(new Date())
                                .setTimestamp(new Date())
                                .setSerialNo(serialNo))
                        .map(newStudent -> {
                            log.info(Constants.STUDENT_OPERATION_SERIAL_NO + serialNo);
                            log.info(newStudent.toString());

                            this.studentHistoryRepository
                                    .save(StudentHistory.builder()
                                            .operationType(Constants.STUDENT_HISTORY_CREATE)
                                            .studentId(student.getId())
                                            .type(student.getType())
                                            .name(student.getName())
                                            .otherName(student.getOtherName())
                                            .region(student.getRegion())
                                            .avatar(student.getAvatar())
                                            .gender(student.getGender())
                                            .birthday(student.getBirthday())
                                            .nationality(student.getNationality())
                                            .politics(student.getPolitics())
                                            .isAtSchool(student.getIsAtSchool())
                                            .health(student.getHealth())
                                            .family(student.getFamily())
                                            .school(student.getSchool())
                                            .grade(student.getGrade())
                                            .period(student.getPeriod())
                                            .idCardNo(student.getIdCardNo())
                                            .schoolRollNo(student.getSchoolRollNo())
                                            .distanceOfSchoolAndHome(student.getDistanceOfSchoolAndHome())
                                            .trafficCondition(student.getTrafficCondition())
                                            .aids(student.getAids())
                                            .demands(student.getDemands())
                                            .solutions(student.getSolutions())
                                            .isPoor(student.getIsPoor())
                                            .createTime(student.getCreateTime())
                                            .timestamp(new Date())
                                            .status(student.getStatus())
                                            .serialNo(serialNo)
                                            .description(student.getDescription())
                                            .build())
                                    .subscribe(studentHistory -> {
                                        log.info(Constants.STUDENT_OPERATION_SERIAL_NO + serialNo);
                                        log.info(studentHistory.toString());
                                    });

                            return newStudent.setStatus(Constants.STUDENT_ERRORCODE_SUCCESS);
                        }));
    }
}
