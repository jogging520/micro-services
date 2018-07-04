package com.northbrain.menu.service;

import com.northbrain.menu.repository.ICmsMenuHistoryRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import com.northbrain.menu.model.Constants;
import com.northbrain.menu.model.CmsMenu;
import com.northbrain.menu.repository.ICmsMenuRepository;

import reactor.core.publisher.Flux;

@Service
@Log
public class MenuService {
    private final ICmsMenuRepository cmsMenuRepository;
    private final ICmsMenuHistoryRepository cmsMenuHistoryRepository;

    public MenuService(ICmsMenuRepository cmsMenuRepository, ICmsMenuHistoryRepository cmsMenuHistoryRepository) {
        this.cmsMenuRepository = cmsMenuRepository;
        this.cmsMenuHistoryRepository = cmsMenuHistoryRepository;
    }

    /**
     * 方法：获取CMS系统的菜单列表
     * @param serialNo 操作流水号
     * @return 菜单
     */
    public Flux<CmsMenu> queryCmsMenus(String serialNo) {
        return this.cmsMenuRepository
                .findAll()
                .map(cmsMenu -> {
                    log.info(Constants.MENU_CMS_OPERATION_SERIAL_NO + serialNo);
                    log.info(cmsMenu.toString());

                    return cmsMenu;
                });
    }
}
