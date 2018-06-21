package com.northbrain.menu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.northbrain.menu.model.Constants;
import com.northbrain.menu.model.Menu;
import com.northbrain.menu.service.MenuService;

import reactor.core.publisher.Mono;

@RestController
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 方法：获取特定系统的菜单列表
     * @param appType 应用类型，如:CMS、WEB、APP、WECHAT
     * @param roleId 角色编号
     * @return 特定的菜单流
     */
    @GetMapping(Constants.MENU_HTTP_REQUEST_MAPPING)
    public Mono<Menu> queryMenus(@RequestParam String appType,
                                 @RequestParam String roleId) {
        return this.menuService
                .queryMenus(appType, roleId);
    }
}
