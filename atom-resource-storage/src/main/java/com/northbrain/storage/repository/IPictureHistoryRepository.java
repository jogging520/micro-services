package com.northbrain.storage.repository;

import com.northbrain.storage.model.PictureHistory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IPictureHistoryRepository extends ReactiveCrudRepository<PictureHistory, String> {
}
