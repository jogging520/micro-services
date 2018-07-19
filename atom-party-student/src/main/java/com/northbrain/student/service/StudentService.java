package com.northbrain.student.service;

import com.northbrain.student.model.Constants;
import com.northbrain.student.model.Student;
import com.northbrain.student.model.StudentHistory;
import com.northbrain.student.repository.IStudentHistoryRepository;
import com.northbrain.student.repository.IStudentRepository;
import com.northbrain.util.timer.Clock;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
     * @param category 类别（企业）
     * @param regions 区域数组
     * @return 用户信息列表
     */
    public Flux<Student> queryStudentsByRegions(String serialNo,
                                                String category,
                                                String[] regions) {
        return this.studentRepository
                .findByCategoryAndStatusAndRegionIn(category,
                        Constants.STUDENT_STATUS_ACTIVE, regions)
                .map(student -> {
                    log.info(Constants.STUDENT_OPERATION_SERIAL_NO + serialNo);
                    log.info(student.toString());
                    return student;
                });
    }

    /**
     * 方法：根据学生编号查询学生信息
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @param studentId 学生编号
     * @return 学生信息
     */
    public Mono<Student> queryStudentById(String serialNo,
                                          String category,
                                          String studentId) {
        return this.studentRepository
                .findById(studentId)
                .filter(student -> student.getCategory().equalsIgnoreCase(category))
                .map(student -> {
                    log.info(Constants.STUDENT_OPERATION_SERIAL_NO + serialNo);
                    log.info(student.toString());
                    return student;
                });
    }

    /**
     * 方法：根据家庭信息查找学生信息
     * @param serialNo 操作流水号
     * @param category 类别（企业）
     * @param family 家庭编号
     * @return 学生信息
     */
    public Flux<Student> queryStudentsByFamily(String serialNo,
                                               String category,
                                               String family) {
        return this.studentRepository
                .findByCategoryAndStatusAndFamily(category,
                        Constants.STUDENT_STATUS_ACTIVE, family)
                .map(student -> {
                    log.info(Constants.STUDENT_OPERATION_SERIAL_NO + serialNo);
                    log.info(student.toString());
                    return student;
                });
    }

    /**
     * 方法：根据家庭信息查找学生信息
     * @param serialNo 操作流水号
     * @param category 类别（企业）
     * @param school 学校编号
     * @return 学生信息
     */
    public Flux<Student> queryStudentsBySchool(String serialNo,
                                               String category,
                                               String school) {
        return this.studentRepository
                .findByCategoryAndStatusAndSchool(category,
                        Constants.STUDENT_STATUS_ACTIVE, school)
                .map(student -> {
                    log.info(Constants.STUDENT_OPERATION_SERIAL_NO + serialNo);
                    log.info(student.toString());
                    return student;
                });
    }

    /**
     * 方法：创建学生信息
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @param students 学生列表
     * @return 创建成功的学生列表
     */
    public Flux<Student> createStudents(String serialNo,
                                        String category,
                                        Flux<Student> students) {
        return students
                .flatMap(student -> this.studentRepository
                        .findByCategoryAndStatusAndNameAndFamily(category,
                                Constants.STUDENT_STATUS_ACTIVE, student.getName(), student.getFamily())
                        .map(newStudent -> newStudent.setStatus(Constants.STUDENT_ERRORCODE_HAS_EXISTS))
                        .switchIfEmpty(this.studentRepository
                                .save(student
                                        .setCategory(category)
                                        .setStatus(Constants.STUDENT_STATUS_ACTIVE)
                                        .setCreateTime(Clock.currentTime())
                                        .setTimestamp(Clock.currentTime())
                                        .setSerialNo(serialNo))
                                .map(newStudent -> {
                                    log.info(Constants.STUDENT_OPERATION_SERIAL_NO + serialNo);
                                    log.info(newStudent.toString());

                                    this.studentHistoryRepository
                                            .save(StudentHistory.builder()
                                                    .operationType(Constants.STUDENT_HISTORY_CREATE)
                                                    .studentId(newStudent.getId())
                                                    .type(newStudent.getType())
                                                    .category(newStudent.getCategory())
                                                    .name(newStudent.getName())
                                                    .otherName(newStudent.getOtherName())
                                                    .region(newStudent.getRegion())
                                                    .avatar(newStudent.getAvatar())
                                                    .gender(newStudent.getGender())
                                                    .birthday(newStudent.getBirthday())
                                                    .nationality(newStudent.getNationality())
                                                    .politics(newStudent.getPolitics())
                                                    .isAtSchool(newStudent.getIsAtSchool())
                                                    .health(newStudent.getHealth())
                                                    .family(newStudent.getFamily())
                                                    .school(newStudent.getSchool())
                                                    .grade(newStudent.getGrade())
                                                    .period(newStudent.getPeriod())
                                                    .idCardNo(newStudent.getIdCardNo())
                                                    .schoolRollNo(newStudent.getSchoolRollNo())
                                                    .distanceOfSchoolAndHome(newStudent.getDistanceOfSchoolAndHome())
                                                    .trafficCondition(newStudent.getTrafficCondition())
                                                    .aids(newStudent.getAids())
                                                    .demands(newStudent.getDemands())
                                                    .solutions(newStudent.getSolutions())
                                                    .isPoor(newStudent.getIsPoor())
                                                    .createTime(newStudent.getCreateTime())
                                                    .timestamp(Clock.currentTime())
                                                    .status(newStudent.getStatus())
                                                    .serialNo(serialNo)
                                                    .description(newStudent.getDescription())
                                                    .build())
                                            .subscribe(studentHistory -> {
                                                log.info(Constants.STUDENT_OPERATION_SERIAL_NO + serialNo);
                                                log.info(studentHistory.toString());
                                            });

                                    return newStudent.setStatus(Constants.STUDENT_ERRORCODE_SUCCESS);
                                })
                        ));
    }
}
