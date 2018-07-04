package com.northbrain.solution.service;

import com.northbrain.solution.model.Constants;
import com.northbrain.solution.model.Solution;
import com.northbrain.solution.repository.ISolutionRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Log
public class SolutionService {
    private final ISolutionRepository solutionRepository;

    public SolutionService(ISolutionRepository solutionRepository) {
        this.solutionRepository = solutionRepository;
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
}
