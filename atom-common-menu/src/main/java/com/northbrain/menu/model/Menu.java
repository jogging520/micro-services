package com.northbrain.menu.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain=true)
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    @Id
    private String              id;                 //菜单ID
    @NotNull
    private String              type;               //菜单类型：CMS、WEB、APP、WECHAT
    @NotNull
    private String              roleId;             //角色编号（根据不同的角色进行菜单控制，避免部分前端不支持ACL）
    private String              text;               //菜单文本内容
    private Boolean             group;              //是否菜单组
    private String              link;               //angular 路由
    private String              externalLink;       //外部链接
    private String              target;             //链接 target '_blank' | '_self' | '_parent' | '_top'
    private String              icon;               //图标
    private Integer             badge;              //徽标数，展示的数字。（注：`group:true` 无效）
    private Boolean             badge_dot;          //徽标数，显示小红点
    private String              badge_status;       //徽标数，设置 Badge 颜色 （默认：error， 所有颜色值见：https://github.com/cipchk/ng-alain/blob/master/_documents/utils.md#色彩）
    private Boolean             hide;               //是否隐藏
    private Boolean             shortcut;           //是否快捷菜单项
    private Boolean             shortcut_root;      //快捷菜单根节点
    @NotNull
    private Date                createTime;         //创建时间
    @NotNull
    private Date                timestamp;          //状态时间
    @NotNull
    private String              status;             //状态
    @NotNull
    private String              operationId;        //操作流水号
    private String              description;        //描述
    private Menu[]              children;           //二级菜单
}
