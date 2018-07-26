package com.northbrain.task.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@Log
public class TaskService {
    private final KafkaTemplate kafkaTemplate;
}
