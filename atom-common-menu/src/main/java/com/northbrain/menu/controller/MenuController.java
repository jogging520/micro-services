package com.northbrain.menu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
     * 方法：根据类型获取各类系统的菜单列表
     * @param appType 应用类型
     * @param serialNo 操作流水号
     * @return 菜单
     */
    @GetMapping(Constants.MENU_SPECIFIED_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Flux<CmsMenu>> queryMenus(@PathVariable String appType,
                                                    @RequestParam String serialNo) {

        switch (appType) {
            case Constants.MENU_TYPE_CMS:
                return ResponseEntity
                        .ok()
                        .body(this.menuService
                                .queryCmsMenus(serialNo));

            default:
                return ResponseEntity
                        .ok()
                        .body(Flux.empty());
        }
    }
}
