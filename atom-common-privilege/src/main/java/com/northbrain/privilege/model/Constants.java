package com.northbrain.privilege.model;

public class Constants {
    /**
     * 枚举值定义，如状态、类型
     */
    public final static String PRIVILEGE_STATUS_ACTIVE                              =   "ACTIVE";
    public final static String PRIVILEGE_HISTORY_CREATE                             =   "CREATE";   //历史归档：创建
    public final static String PRIVILEGE_HISTORY_UPDATE                             =   "UPDATE";   //历史归档：更新
    public final static String PRIVILEGE_HISTORY_DELETE                             =   "DELETE";   //历史归档：删除

    /**
     * restful资源定义
     */
    public final static String PRIVILEGE_ROLE_HTTP_REQUEST_MAPPING                  =   "/privileges/roles";
    public final static String PRIVILEGE_PERMISSION_HTTP_REQUEST_MAPPING            =   "/privileges/permissions";

    /**
     * 操作定义
     */
    public final static String PRIVILEGE_OPERATION_SERIAL_NO                        =   "本次操作角色实体的流水号为：";

    /**
     * 错误码定义
     */
    public final static String PRIVILEGE_ERRORCODE_SUCCESS                          =   "CODE_COMMON_PRIVILEGE_000";
    public final static String PRIVILEGE_ERRORCODE_HAS_EXISTS                       =   "CODE_COMMON_PRIVILEGE_001";
}
