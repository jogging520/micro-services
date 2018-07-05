package com.northbrain.operation.model;

public class Constants {
    /**
     * 枚举值定义，如状态、类型
     */
    public final static String  OPERATION_STATUS_ACTIVE                         =   "ACTIVE";   //操作记录状态：在用

    /**
     * restful资源定义
     */
    public final static String  OPERATION_HTTP_REQUEST_MAPPING                  =   "/operations";   //操作记录rest资源
    public final static String  OPERATION_SPECIFIED_HTTP_REQUEST_MAPPING        =   "/operations/{serialNo}";   //操作记录rest资源
    public final static String  OPERATION_RECORD_HTTP_REQUEST_MAPPING           =   "/operations/records";   //操作记录rest资源

    /**
     * 操作定义
     */
    public final static String  OPERATION_OPERATION_SERIAL_NO                   =   "本次操作操作记录实体的流水号为：";

    /**
     * 错误码定义
     */
    public final static String  OPERATION_ERRORCODE_SUCCESS                     =   "CODE_COMMON_OPERATION_000";
}
