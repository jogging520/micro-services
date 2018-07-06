package com.northbrain.solution.service;

import com.northbrain.solution.model.Constants;
import com.northbrain.solution.model.Solution;
import com.northbrain.solution.model.SolutionHistory;
import com.northbrain.solution.repository.ISolutionHistoryRepository;
import com.northbrain.solution.repository.ISolutionRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Date;

@Service
@Log
public class SolutionService {
    private final ISolutionRepository solutionRepository;
    private final ISolutionHistoryRepository solutionHistoryRepository;

    public SolutionService(ISolutionRepository solutionRepository,
                           ISolutionHistoryRepository solutionHistoryRepository) {
        this.solutionRepository = solutionRepository;
        this.solutionHistoryRepository = solutionHistoryRepository;
    }

    /**
     * 方法：查询在用的所以解决方案
     * @param serialNo 流水号
     * @return 解决方案
     */
    public Flux<Solution> querySolutions(String serialNo) {
        return this.solutionRepository
                .findByStatus(Constants.SOLUTION_STATUS_ACTIVE)
                .map(solution -> {
                    log.info(Constants.SOLUTION_OPERATION_SERIAL_NO + serialNo);
                    log.info(solution.toString());
                    return solution;
                });
    }

    /**
     * 方法：创建解决方案
     * @param serialNo 流水号
     * @param solutions 解决方案
     * @return 创建成功的解决方案
     */
    public Flux<Solution> createSolutions(String serialNo,
                                          Flux<Solution> solutions) {
        return solutions
                .flatMap(solution -> this.solutionRepository
                        .save(solution
                                .setStatus(Constants.SOLUTION_STATUS_ACTIVE)
                                .setCreateTime(new Date())
                                .setTimestamp(new Date())
                                .setSerialNo(serialNo))
                        .map(newSolution -> {
                            log.info(Constants.SOLUTION_OPERATION_SERIAL_NO + serialNo);
                            log.info(newSolution.toString());

                            this.solutionHistoryRepository
                                    .save(SolutionHistory.builder()
                                            .operationType(Constants.SOLUTION_HISTORY_CREATE)
                                            .solutionId(newSolution.getId())
                                            .type(newSolution.getType())
                                            .organizations(newSolution.getOrganizations())
                                            .schools(newSolution.getSchools())
                                            .families(newSolution.getFamilies())
                                            .items(newSolution.getItems())
                                            .createTime(newSolution.getCreateTime())
                                            .timestamp(new Date())
                                            .status(newSolution.getStatus())
                                            .serialNo(serialNo)
                                            .description(newSolution.getDescription())
                                            .build())
                                    .subscribe(solutionHistory -> {
                                        log.info(Constants.SOLUTION_OPERATION_SERIAL_NO + serialNo);
                                        log.info(solutionHistory.toString());
                                    });

                            return newSolution.setStatus(Constants.SOLUTION_ERRORCODE_SUCCESS);
                        }));
    }
}
