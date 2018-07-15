package com.northbrain.util.tracer;

import lombok.extern.java.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

@Log
public class StackTracer {
    /**
     * 方法：获取异常Exception详细信息
     * @param exception 异常
     */
    public static void printException(Exception exception)
    {
        StringWriter stringWriter;
        PrintWriter printWriter;

        try
        {
            stringWriter = new StringWriter();
            printWriter = new PrintWriter(stringWriter);

            exception.printStackTrace(printWriter);

            log.info(stringWriter.toString());
        }
        catch(Exception error)
        {
            log.info(error.getMessage());
        }
    }
}
