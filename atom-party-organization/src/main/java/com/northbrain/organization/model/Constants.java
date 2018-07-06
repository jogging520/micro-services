package com.northbrain.organization.model;

public class Constants {
    /**
     * 枚举值定义，如状态、类型
     */
    public final static String ORGANIZATION_STATUS_ACTIVE                               =   "ACTIVE";   //用户状态：在用
    public final static String ORGANIZATION_HISTORY_CREATE                              =   "CREATE";   //历史归档：创建
    public final static String ORGANIZATION_HISTORY_UPDATE                              =   "UPDATE";   //历史归档：更新
    public final static String ORGANIZATION_HISTORY_DELETE                              =   "DELETE";   //历史归档：删除

    /**
     * restful资源定义
     */
    public final static String ORGANIZATION_HTTP_REQUEST_MAPPING                        =   "/organizations";
    public final static String ORGANIZATION_REGION_HTTP_REQUEST_MAPPING                 =   "/organizations/regions";

    /**
     * 操作定义
     */
    public final static String ORGANIZATION_OPERATION_SERIAL_NO                         =   "本次操作组织机构实体的流水号为：";

    /**
     * 错误码定义
     */
    public final static String ORGANIZATION_ERRORCODE_SUCCESS                           =   "SUCCESS";

}
