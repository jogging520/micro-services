package com.northbrain.task.controller;

import com.northbrain.task.model.Constants;
import com.northbrain.task.model.Message;
import com.northbrain.task.service.TaskService;
import com.northbrain.util.security.Crypt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/keypair")
    public void hello6() {
        Crypt.generateRSAKeyPair();
    }

    /**
     * 方法：发送消息
     * 发送至kafka的相应topic，同时存储至库中。
     * @param serialNo 流水号
     * @param category 分类（企业）
     * @param messages 消息
     * @return 无
     */
    @PostMapping(Constants.TASK_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Void>> sendMessages(@RequestParam String serialNo,
                                                   @RequestParam String category,
                                                   @RequestBody Flux<Message> messages) {
        return ResponseEntity.ok()
                .body(this.taskService
                        .sendMessages(serialNo, category, messages));
    }
}
