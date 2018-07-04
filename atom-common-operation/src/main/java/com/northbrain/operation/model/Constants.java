package com.northbrain.operation.model;

public class Constants {
    public final static String  OPERATION_STATUS_ACTIVE                         =   "ACTIVE";   //操作记录状态：在用

    public final static String  OPERATION_HTTP_REQUEST_MAPPING                  =   "/operations";   //操作记录rest资源
    public final static String  OPERATION_SPECIFIED_HTTP_REQUEST_MAPPING        =   "/operations/{serialNo}";   //操作记录rest资源
    public final static String  OPERATION_RECORD_HTTP_REQUEST_MAPPING           =   "/operations/records";   //操作记录rest资源

    public final static String  OPERATION_OPERATION_SERIAL_NO                   =   "本次操作操作记录实体的流水号为：";
}
