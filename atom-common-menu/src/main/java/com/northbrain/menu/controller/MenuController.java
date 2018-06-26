package com.northbrain.menu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.northbrain.menu.model.Constants;
import com.northbrain.menu.model.CmsMenu;
import com.northbrain.menu.service.MenuService;

import reactor.core.publisher.Flux;

@RestController
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 方法：获取CMS系统的菜单列表
     * @return 特定的菜单流
     */
    @GetMapping(Constants.MENU_CMS_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<CmsMenu>> queryCmsMenus() {
        return ResponseEntity
                .ok()
                .body(this.menuService
                        .queryCmsMenus());
    }
}
