package com.northbrain.search.model;

public class Constants {
    /**
     * 枚举值定义，如状态、类型
     */
    public final static String  SEARCH_STATUS_ACTIVE                    =   "ACTIVE";   //搜索状态：在用
    public final static String  SEARCH_HISTORY_CREATE                   =   "CREATE";   //历史归档：创建
    public final static String  SEARCH_HISTORY_UPDATE                   =   "UPDATE";   //历史归档：更新
    public final static String  SEARCH_HISTORY_DELETE                   =   "DELETE";   //历史归档：删除

    public final static String  SEARCH_SUMMARY_INDEX_NAME               =   "northbrain";  //index名称
    public final static String  SEARCH_SUMMARY_TYPE_NAME                =   "summary";  //type名称
    public final static String  SEARCH_SUMMARY_REFRESH_INTERVAL         =   "-1";       //刷新间隔
    public final static String  SEARCH_SUMMARY_ANALYZER_PINYIN          =   "pinyin_analyzer";  //拼音搜索（分词器）

    public final static String  SEARCH_TYPE_COMMON                      =   "COMMON";   //普通搜索类型
    public final static String  SEARCH_SUMMARY_TYPE_ALL                 =   "ALL";   //全部搜索


    /**
     * restful资源定义
     */
    public final static String  SEARCH_HTTP_REQUEST_MAPPING                   =   "/searches";

    /**
     * 操作定义
     */
    public final static String  SEARCH_OPERATION_SERIAL_NO                    =   "本次操作搜索的流水号为：";
    public final static String  SEARCH_OPERATION_QUERY_CONDITION              =   "本次操作搜索的查询条件为：";

    /**
     * 错误码定义
     */
    public final static String  SEARCH_ERRORCODE_SUCCESS                      =   "SUCCESS";                            //成功操作
    public final static String  SEARCH_ERRORCODE_HAS_EXISTS                   =   "ERROR_SERVICE_SEARCH_HAS_EXISTS";    //已经存在相应的记录
    public final static String  SEARCH_ERRORCODE_NOT_EXISTS                   =   "ERROR_SERVICE_SEARCH_NOT_EXISTS";    //不存在相应的记录

}
