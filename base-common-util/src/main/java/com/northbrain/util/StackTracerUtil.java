package com.northbrain.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类名：StackTracerUtil
 * 用途：跟踪栈打印信息，将栈调用信息打印到日志中。默认情况下输出到标准Out设备中，提供静态方法供全局使用。
 * @author Jiakun
 * @version 1.0
 */
public class StackTracerUtil {
    private final static Logger logger = LoggerFactory.getLogger(StackTracerUtil.class);

    /**
     * 方法：获取异常Exception详细信息
     * @param exception 异常
     * @return 异常信息转换成字符串
     */
    public static String getExceptionInfo(Exception exception) {
        StringWriter stringWriter;
        PrintWriter printWriter;

        try {
            stringWriter = new StringWriter();
            printWriter = new PrintWriter(stringWriter);

            exception.printStackTrace(printWriter);

            return "\r\n" + stringWriter.toString() + "\r\n";
        }
        catch(Exception e) {
            logger.error(String.valueOf(e));
        }

        return null;
    }

    /**
     * 方法：获取异常Throwable详细信息
     * @param throwable 异常
     * @return 异常信息转换成字符串
     */
    public static String getExceptionInfo(Throwable throwable) {
        StringWriter stringWriter;
        PrintWriter printWriter;

        try {
            stringWriter = new StringWriter();
            printWriter = new PrintWriter(stringWriter);

            throwable.printStackTrace(printWriter);

            return "\r\n" + stringWriter.toString() + "\r\n";
        }
        catch(Exception e) {
            logger.error(String.valueOf(e));
        }

        return null;
    }
}
