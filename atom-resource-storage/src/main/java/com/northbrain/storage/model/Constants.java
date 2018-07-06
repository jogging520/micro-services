package com.northbrain.storage.model;

public class Constants {
    /**
     * 枚举值定义，如状态、类型
     */
    public final static String  STORAGE_STATUS_ACTIVE                               =   "ACTIVE";   //用户状态：在用
    public final static String  STORAGE_HISTORY_CREATE                              =   "CREATE";   //历史归档：创建
    public final static String  STORAGE_HISTORY_UPDATE                              =   "UPDATE";   //历史归档：更新
    public final static String  STORAGE_HISTORY_DELETE                              =   "DELETE";   //历史归档：删除

    /**
     * restful资源定义
     */
    public final static String  STORAGE_PICTURE_SPECIFIED_HTTP_REQUEST_MAPPING      =   "/storage/pictures/{pictureId}";
    public final static String  STORAGE_PICTURE_HTTP_REQUEST_MAPPING                =   "/storage/pictures";

    /**
     * 操作定义
     */
    public final static String  STORAGE_OPERATION_SERIAL_NO                         =   "本次操作存储实体的流水号为：";

    /**
     * 错误码定义
     */
    public final static String  STORAGE_ERRORCODE_SUCCESS                           =   "SUCCESS";

}
