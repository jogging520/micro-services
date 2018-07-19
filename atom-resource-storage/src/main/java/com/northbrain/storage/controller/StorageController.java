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

    /**
     * 方法：按照图片ID号查询图片
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @param pictureId 图片ID
     * @return 图片
     */
    @GetMapping(Constants.STORAGE_PICTURE_SPECIFIED_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Picture>> queryPictureById(@RequestParam String serialNo,
                                                          @RequestParam String category,
                                                          @PathVariable String pictureId) {
        return ResponseEntity.ok()
                .body(this.storageService
                        .queryPictureById(serialNo, category, pictureId));
    }

    /**
     * 方法：创建图片
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @param pictures 图片
     * @return 创建成功的图片
     */
    @PostMapping(Constants.STORAGE_PICTURE_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<Picture>> createPictures(@RequestParam String serialNo,
                                                        @RequestParam String category,
                                                        @RequestBody Flux<Picture> pictures) {
        return ResponseEntity.ok()
                .body(this.storageService
                        .createPictures(serialNo, category, pictures));
    }
}
