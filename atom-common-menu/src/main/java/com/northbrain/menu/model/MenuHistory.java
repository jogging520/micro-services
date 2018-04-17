package com.northbrain.menu.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class MenuHistory {
    @Id
    private String      menuHistoryId;      //菜单历史编号
    private String      operationType;      //操作类型：增删改
    private String      menuId;             //菜单ID
    private String      menuType;           //菜单类型：CMS、WEB、APP、WECHAT
    private String      roleId;             //角色编号（根据不同的角色进行菜单控制，避免部分前端不支持ACL）
    private String      text;               //菜单文本内容
    private Boolean     group;              //是否菜单组
    private String      link;               //angular 路由
    private String      externalLink;       //外部链接
    private String      target;             //链接 target '_blank' | '_self' | '_parent' | '_top'
    private String      icon;               //图标
    private Integer     badge;              //徽标数，展示的数字。（注：`group:true` 无效）
    private Boolean     badge_dot;          //徽标数，显示小红点
    private String      badge_status;       //徽标数，设置 Badge 颜色 （默认：error， 所有颜色值见：https://github.com/cipchk/ng-alain/blob/master/_documents/utils.md#色彩）
    private Boolean     hide;               //是否隐藏
    private Boolean     shortcut;           //是否快捷菜单项
    private Boolean     shortcut_root;      //快捷菜单根节点
    private Menu[]      children;           //二级菜单
    private Date        createTime;         //创建时间
    private Date        timestamp;          //时间戳
    private String      status;             //状态
}
