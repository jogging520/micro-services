package com.northbrain.menu.service;

import org.springframework.stereotype.Service;

import com.northbrain.menu.model.Constants;
import com.northbrain.menu.model.Menu;
import com.northbrain.menu.repository.IMenuHistoryRepository;
import com.northbrain.menu.repository.IMenuRepository;

import reactor.core.publisher.Mono;

@Service
public class MenuService {
    private final IMenuRepository menuRepository;
    private final IMenuHistoryRepository menuHistoryRepository;

    public MenuService(IMenuRepository menuRepository, IMenuHistoryRepository menuHistoryRepository) {
        this.menuRepository = menuRepository;
        this.menuHistoryRepository = menuHistoryRepository;
    }

    /**
     * 方法：获取特定系统的菜单列表
     * @param menuType 菜单类型，如:CMS、WEB、APP、WECHAT
     * @param roleId 角色编号
     * @return 特定的菜单流
     */
    public Mono<Menu> selectMenu(String menuType, String roleId) {
        return this.menuRepository
                .findByMenuTypeAndRoleId(menuType, roleId)
                .filter(menu -> menu.getStatus().equalsIgnoreCase(Constants.MENU_STATUS_ACTIVE));
    }
}
