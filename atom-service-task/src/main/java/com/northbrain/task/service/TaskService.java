package com.northbrain.task.service;

import com.google.gson.Gson;
import com.northbrain.task.model.Constants;
import com.northbrain.task.model.Message;
import com.northbrain.task.repository.IMessageRepository;
import com.northbrain.util.timer.Clock;
import lombok.extern.java.Log;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Service
@Log
public class TaskService {
    private final KafkaTemplate kafkaTemplate;
    private final IMessageRepository messageRepository;
    private final Gson gson;

    public TaskService(KafkaTemplate kafkaTemplate,
                       IMessageRepository messageRepository, Gson gson) {
        this.kafkaTemplate = kafkaTemplate;
        this.messageRepository = messageRepository;
        this.gson = gson;
    }

    /**
     * 方法：发送消息
     * 发送至kafka的相应topic，同时存储至库中。
     * @param serialNo 流水号
     * @param category 分类（企业）
     * @param messages 消息
     * @return 无
     */
    public Mono<Void> sendMessages(String serialNo,
                                   String category,
                                   Flux<Message> messages) {
        messages
                .filter(message -> message.getCategory().equalsIgnoreCase(category))
                .subscribe(message -> {
                    log.info(Constants.TASK_OPERATION_SERIAL_NO + serialNo);

                    this.messageRepository
                            .save(message
                                    .setCreateTime(Clock.currentTime())
                                    .setTimestamp(Clock.currentTime())
                                    .setStatus(Constants.TASK_STATUS_ACTIVE)
                                    .setSerialNo(serialNo))
                            .subscribe();

                    this.kafkaTemplate.send(Constants.TASK_MESSAGE_TOPIC,
                            this.gson.toJson(message, Message.class));
                });

        return Mono.empty().then();
    }

    /**
     * 方法：色查询历史消息
     * 根据用户或者角，或者广播消息
     * @param serialNo 流水号
     * @param category 分类（企业）
     * @param user 用户
     * @param role 角色
     * @return 历史消息
     */
    public Flux<Message> queryMessagesByUserOrRole(String serialNo,
                                                   String category,
                                                   String user,
                                                   String role) {
        return this.messageRepository
                .findByCategoryAndStatus(category, Constants.TASK_STATUS_ACTIVE)
                .filter(message -> message.getTo() != null &&
                        (Arrays.asList(message.getTo()).contains(user) ||
                                Arrays.asList(message.getTo()).contains(role) ||
                                Arrays.asList(message.getTo()).contains(Constants.TASK_MESSAGE_BROADCAST)))
                .map(message -> {
                    log.info(Constants.TASK_OPERATION_SERIAL_NO + serialNo);
                    log.info(message.toString());

                    return message;
                });
    }

    @KafkaListener(topics = Constants.TASK_MESSAGE_TOPIC)
    public Mono<Void> processMessage(String jsonMessage) {
        Message message = this.gson.fromJson(jsonMessage, Message.class);

        log.info(message.getContent());

        return Mono.empty().then();
    }
}
