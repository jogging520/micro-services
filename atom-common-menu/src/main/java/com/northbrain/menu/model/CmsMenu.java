package com.northbrain.menu.model;

import java.util.Date;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
@Document
public class CmsMenu {
    @Id
    private String              id;                 //菜单ID
    private String              text;               //菜单文本内容
    private Boolean             group;              //是否菜单组
    private String              link;               //路由
    private Boolean             linkExact;          //路由是否精准匹配
    private String              externalLink;       //外部链接
    private String              target;             //链接 target '_blank' | '_self' | '_parent' | '_top'
    private String              icon;               //图标
    private Integer             badge;              //徽标数，展示的数字。（注：`group:true` 无效）
    private Boolean             badge_dot;          //徽标数，显示小红点
    private String              badge_status;       //徽标数，设置 Badge 颜色 （默认：error， 所有颜色值见：https://github.com/cipchk/ng-alain/blob/master/_documents/utils.md#色彩）
    private Boolean             hide;               //是否隐藏
    private Boolean             hideInBreadcrumb;   //隐藏面包屑，指 `page-header` 组件的自动生成面包屑时有效
    private Integer[]           acl;                //ACL配置，若导入 `@delon/acl` 时自动有效，等同于 `ACLService.can(roleOrAbility: ACLCanType)` 参数值
    private Boolean             shortcut;           //是否快捷菜单项
    private Boolean             shortcut_root;      //快捷菜单根节点
    private Boolean             reuse;              //是否允许复用，需配合 `reuse-tab` 组件
    private CmsMenu[]           children;           //二级菜单
    @NotNull
    private Date                createTime;         //创建时间
    @NotNull
    private Date                timestamp;          //状态时间
    @NotNull
    private String              status;             //状态
    @NotNull
    private String              serialNo;           //操作流水号
    private String              description;        //描述
}
