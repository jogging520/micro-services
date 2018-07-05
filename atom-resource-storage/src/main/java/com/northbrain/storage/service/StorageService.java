package com.northbrain.storage.service;

import com.northbrain.storage.model.Constants;
import com.northbrain.storage.model.Picture;
import com.northbrain.storage.repository.IPictureRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
@Log
public class StorageService {
    private final IPictureRepository pictureRepository;

    public StorageService(IPictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    /**
     * 方法：按照图片ID号查询图片
     * @param serialNo 流水号
     * @param pictureId 图片ID
     * @return 图片
     */
    public Mono<Picture> queryPictureById(String serialNo,
                                          String pictureId) {
        return this.pictureRepository
                .findById(pictureId)
                .filter(picture -> picture.getStatus().equalsIgnoreCase(Constants.STORAGE_STATUS_ACTIVE))
                .map(picture -> {
                    log.info(Constants.STORAGE_OPERATION_SERIAL_NO + serialNo);
                    log.info(picture.toString());
                    return picture;
                });
    }

    /**
     * 方法：创建图片
     * @param serialNo 流水号
     * @param pictures 图片
     * @return 创建成功的图片
     */
    public Flux<Picture> createPictures(String serialNo,
                                        Flux<Picture> pictures) {
        return pictures
                .map(picture -> picture
                        .setId(null)
                        .setStatus(Constants.STORAGE_STATUS_ACTIVE)
                        .setCreateTime(new Date())
                        .setTimestamp(new Date())
                        .setSerialNo(serialNo))
                .flatMap(picture -> this.pictureRepository.save(picture))
                .map(picture -> {
                    log.info(Constants.STORAGE_OPERATION_SERIAL_NO + serialNo);
                    log.info(picture.toString());
                    return picture;
                });
    }
}
