package com.northbrain.family.model;

public class Constants {
    public final static String  FAMILY_STATUS_ACTIVE                          =   "ACTIVE";   //用户状态：在用
    public final static String  FAMILY_HISTORY_CREATE                             =   "CREATE";   //历史归档：创建
    public final static String  FAMILY_HISTORY_UPDATE                             =   "UPDATE";   //历史归档：更新
    public final static String  FAMILY_HISTORY_DELETE                             =   "DELETE";   //历史归档：删除

    public final static String  FAMILY_HTTP_REQUEST_MAPPING                   =   "/families";
    public final static String  FAMILY_SPECIFIED_HTTP_REQUEST_MAPPING         =   "/families/{familyId}";

    public final static String  FAMILY_OPERATION_SERIAL_NO                    =   "本次操作家庭实体的流水号为：";
}
