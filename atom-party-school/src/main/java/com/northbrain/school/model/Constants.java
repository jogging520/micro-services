package com.northbrain.school.model;

public class Constants {
    /**
     * 枚举值定义，如状态、类型
     */
    public final static String  SCHOOL_STATUS_ACTIVE                          =   "ACTIVE";   //用户状态：在用
    public final static String  SCHOOL_HISTORY_CREATE                         =   "CREATE";   //历史归档：创建
    public final static String  SCHOOL_HISTORY_UPDATE                         =   "UPDATE";   //历史归档：更新
    public final static String  SCHOOL_HISTORY_DELETE                         =   "DELETE";   //历史归档：删除

    /**
     * restful资源定义
     */
    public final static String  SCHOOL_HTTP_REQUEST_MAPPING                   =   "/schools";
    public final static String  SCHOOL_SPECIFIED_ID_HTTP_REQUEST_MAPPING      =   "/schools/{schoolId}";
    public final static String  SCHOOL_SPECIFIED_NAME_HTTP_REQUEST_MAPPING    =   "/schools/name";

    /**
     * 操作定义
     */
    public final static String  SCHOOL_OPERATION_SERIAL_NO                    =   "本次操作学校实体的流水号为：";

    /**
     * 错误码定义
     */
    public final static String  SCHOOL_ERRORCODE_SUCCESS                      =   "SUCCESS";

}
