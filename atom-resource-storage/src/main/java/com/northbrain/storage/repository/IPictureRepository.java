package com.northbrain.storage.repository;

import com.northbrain.storage.model.Picture;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IPictureRepository extends ReactiveCrudRepository<Picture, String> {
}
