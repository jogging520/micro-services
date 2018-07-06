package com.northbrain.strategy.model;

public class Constants {
    /**
     * 枚举值定义，如状态、类型
     */
    public final static String STRATEGY_STATUS_ACTIVE                           =   "ACTIVE";

    public final static String STRATEGY_HISTORY_CREATE                          =   "CREATE";   //历史归档：创建
    public final static String STRATEGY_HISTORY_UPDATE                          =   "UPDATE";   //历史归档：更新
    public final static String STRATEGY_HISTORY_DELETE                          =   "DELETE";   //历史归档：删除

    public final static String STRATEGY_TYPE_APPLICATION                        =   "application";
    public final static String STRATEGY_TYPE_ERRORCODE                          =   "errorcode";

    /**
     * restful资源定义
     */
    public final static String STRATEGY_HTTP_REQUEST_MAPPING                    =   "/strategies";
    public final static String STRATEGY_APPLICATION_HTTP_REQUEST_MAPPING        =   "/strategies/application";
    public final static String STRATEGY_ERRORCODE_HTTP_REQUEST_MAPPING          =   "/strategies/errorcode";

    /**
     * 操作定义
     */
    public final static String STRATEGY_OPERATION_SERIAL_NO                     =   "本次操作策略实体的流水号为：";

    /**
     * 错误码定义
     */
    public final static String STRATEGY_ERRORCODE_SUCCESS                       =   "SUCCESS";
}
