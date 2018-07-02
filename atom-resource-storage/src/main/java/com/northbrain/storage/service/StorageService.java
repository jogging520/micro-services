package com.northbrain.storage.service;

import com.northbrain.storage.model.Constants;
import com.northbrain.storage.model.Picture;
import com.northbrain.storage.repository.IPictureRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class StorageService {
    private final IPictureRepository pictureRepository;

    public StorageService(IPictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public Mono<Picture> queryPictureById(String operationId,
                                          String pictureId) {
        return this.pictureRepository
                .findById(pictureId)
                .filter(picture -> picture.getStatus().equalsIgnoreCase(Constants.STORAGE_STATUS_ACTIVE))
                .log(operationId);
    }

    public Flux<Picture> createPictures(String operationId,
                                        Flux<Picture> pictures) {
        return pictures
                .map(picture -> picture
                        .setStatus(Constants.STORAGE_STATUS_ACTIVE)
                        .setCreateTime(new Date())
                        .setTimestamp(new Date())
                        .setOperationId(operationId))
                .flatMap(picture -> this.pictureRepository.save(picture));
    }
}
