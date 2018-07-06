package com.northbrain.solution.model;

public class Constants {
    /**
     * 枚举值定义，如状态、类型
     */
    public final static String  SOLUTION_STATUS_ACTIVE                          =   "ACTIVE";   //用户状态：在用
    public final static String  SOLUTION_HISTORY_CREATE                         =   "CREATE";   //历史归档：创建
    public final static String  SOLUTION_HISTORY_UPDATE                         =   "UPDATE";   //历史归档：更新
    public final static String  SOLUTION_HISTORY_DELETE                         =   "DELETE";   //历史归档：删除

    /**
     * restful资源定义
     */
    public final static String  SOLUTION_HTTP_REQUEST_MAPPING                   =   "/solutions";

    /**
     * 操作定义
     */
    public final static String  SOLUTION_OPERATION_SERIAL_NO                    =   "本次操作解决方案实体的流水号为：";

    /**
     * 错误码定义
     */
    public final static String  SOLUTION_ERRORCODE_SUCCESS                      =   "SUCCESS";

}
