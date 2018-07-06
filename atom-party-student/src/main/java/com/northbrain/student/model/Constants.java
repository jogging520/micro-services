package com.northbrain.student.model;

public class Constants {
    /**
     * 枚举值定义，如状态、类型
     */
    public final static String STUDENT_STATUS_ACTIVE                            =   "ACTIVE";            //正常用户状态
    public final static String STUDENT_HISTORY_CREATE                             =   "CREATE";   //历史归档：创建
    public final static String STUDENT_HISTORY_UPDATE                             =   "UPDATE";   //历史归档：更新
    public final static String STUDENT_HISTORY_DELETE                             =   "DELETE";   //历史归档：删除

    /**
     * restful资源定义
     */
    public final static String STUDENT_HTTP_REQUEST_MAPPING                     =   "/students";
    public final static String STUDENT_SPECIFIED_HTTP_REQUEST_MAPPING           =   "/students/{studentId}";

    /**
     * 操作定义
     */
    public final static String STUDENT_OPERATION_SERIAL_NO                      =   "本次操作学生实体的流水号为：";

    /**
     * 错误码定义
     */
    public final static String STUDENT_ERRORCODE_SUCCESS                          =   "SUCCESS";

}
