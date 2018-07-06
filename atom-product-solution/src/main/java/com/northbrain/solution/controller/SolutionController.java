package com.northbrain.solution.controller;

import com.northbrain.solution.model.Constants;
import com.northbrain.solution.model.Solution;
import com.northbrain.solution.service.SolutionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
public class SolutionController {
    private final SolutionService solutionService;

    public SolutionController(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    /**
     * 方法：查询在用的所以解决方案
     * @param serialNo 流水号
     * @return 解决方案
     */
    @GetMapping(Constants.SOLUTION_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Solution>> querySolutions(@RequestParam String serialNo) {
        return ResponseEntity.ok()
                .body(this.solutionService
                        .querySolutions(serialNo));
    }

    /**
     * 方法：创建解决方案
     * @param serialNo 流水号
     * @param solutions 解决方案
     * @return 创建成功的解决方案
     */
    @PostMapping(Constants.SOLUTION_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Solution>> createSolutions(@RequestParam String serialNo,
                                                          @RequestBody Flux<Solution> solutions) {
        return ResponseEntity.ok()
                .body(this.solutionService
                        .createSolutions(serialNo, solutions));
    }
}
