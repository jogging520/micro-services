package com.northbrain.menu.repository;

import com.northbrain.menu.model.CmsMenuHistory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ICmsMenuHistoryRepository extends ReactiveCrudRepository<CmsMenuHistory, String> {
}
