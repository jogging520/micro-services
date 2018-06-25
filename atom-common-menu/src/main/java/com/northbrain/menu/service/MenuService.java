package com.northbrain.menu.service;

import com.northbrain.menu.repository.ICmsMenuHistoryRepository;
import org.springframework.stereotype.Service;

import com.northbrain.menu.model.Constants;
import com.northbrain.menu.model.CmsMenu;
import com.northbrain.menu.repository.ICmsMenuRepository;

import reactor.core.publisher.Flux;

@Service
public class MenuService {
    private final ICmsMenuRepository cmsMenuRepository;
    private final ICmsMenuHistoryRepository cmsMenuHistoryRepository;

    public MenuService(ICmsMenuRepository cmsMenuRepository, ICmsMenuHistoryRepository cmsMenuHistoryRepository) {
        this.cmsMenuRepository = cmsMenuRepository;
        this.cmsMenuHistoryRepository = cmsMenuHistoryRepository;
    }

    /**
     * 方法：获取CMS系统的菜单列表
     * @return 特定的菜单流
     */
    public Flux<CmsMenu> queryCmsMenus() {
        return this.cmsMenuRepository
                .findAll()
                .filter(menu -> menu.getStatus().equalsIgnoreCase(Constants.MENU_STATUS_ACTIVE));
    }
}
