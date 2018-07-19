package com.northbrain.storage.service;

import com.northbrain.storage.model.Constants;
import com.northbrain.storage.model.Picture;
import com.northbrain.storage.model.PictureHistory;
import com.northbrain.storage.repository.IPictureHistoryRepository;
import com.northbrain.storage.repository.IPictureRepository;
import com.northbrain.util.timer.Clock;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Log
public class StorageService {
    private final IPictureRepository pictureRepository;
    private final IPictureHistoryRepository pictureHistoryRepository;

    public StorageService(IPictureRepository pictureRepository,
                          IPictureHistoryRepository pictureHistoryRepository) {
        this.pictureRepository = pictureRepository;
        this.pictureHistoryRepository = pictureHistoryRepository;
    }

    /**
     * 方法：按照图片ID号查询图片
     * @param serialNo 流水号
     * @param category 类别（企业）
     * @param pictureId 图片ID
     * @return 图片
     */
    public Mono<Picture> queryPictureById(String serialNo,
                                          String category,
                                          String pictureId) {
        return this.pictureRepository
                .findById(pictureId)
                .filter(picture -> picture.getCategory().equalsIgnoreCase(category))
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
     * @param category 类别（企业）
     * @param pictures 图片
     * @return 创建成功的图片
     */
    public Flux<Picture> createPictures(String serialNo,
                                        String category,
                                        Flux<Picture> pictures) {
        return pictures
                .flatMap(picture ->
                        this.pictureRepository
                                .save(picture
                                        .setId(null)
                                        .setCategory(category)
                                        .setStatus(Constants.STORAGE_STATUS_ACTIVE)
                                        .setCreateTime(Clock.currentTime())
                                        .setTimestamp(Clock.currentTime())
                                        .setSerialNo(serialNo)))
                .map(newPicture -> {
                    log.info(Constants.STORAGE_OPERATION_SERIAL_NO + serialNo);
                    log.info(newPicture.toString());

                    this.pictureHistoryRepository
                            .save(PictureHistory.builder()
                                    .operationType(Constants.STORAGE_HISTORY_CREATE)
                                    .pictureId(newPicture.getId())
                                    .type(newPicture.getType())
                                    .category(newPicture.getCategory())
                                    .content(newPicture.getContent())
                                    .createTime(newPicture.getCreateTime())
                                    .timestamp(Clock.currentTime())
                                    .status(newPicture.getStatus())
                                    .serialNo(serialNo)
                                    .description(newPicture.getDescription())
                                    .build())
                            .subscribe(pictureHistory -> {
                                log.info(Constants.STORAGE_OPERATION_SERIAL_NO + serialNo);
                                log.info(pictureHistory.toString());
                            });

                    return newPicture.setStatus(Constants.STORAGE_ERRORCODE_SUCCESS);
                });
    }
}
