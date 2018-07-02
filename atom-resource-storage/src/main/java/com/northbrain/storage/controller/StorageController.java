package com.northbrain.storage.controller;

import com.northbrain.storage.model.Constants;
import com.northbrain.storage.model.Picture;
import com.northbrain.storage.service.StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class StorageController {
    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping(Constants.STORAGE_PICTURE_SPECIFIED_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Picture>> queryPictureById(@RequestParam String operationId,
                                                          @PathVariable String pictureId) {
        return ResponseEntity.ok()
                .body(this.storageService
                        .queryPictureById(operationId, pictureId));
    }

    @PostMapping(Constants.STORAGE_PICTURE_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Picture>> createPictures(@RequestParam String operationId,
                                                        @RequestBody Flux<Picture> pictures) {
        return ResponseEntity.ok()
                .body(this.storageService
                        .createPictures(operationId, pictures));
    }
}
