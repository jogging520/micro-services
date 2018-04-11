package com.northbrain.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 类名：LogTracerUtil
 * 用途：日志跟踪器，利用spring的aop打印方法调用的开始时间和结束时间，以及耗时。
 * @version 1.0
 */
@Aspect
@Component
@Order(1)
public class LogTracerUtil {
    private final Logger logger = LoggerFactory.getLogger(LogTracerUtil.class);

    /**
     * 定义一个切入点.
     *
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二个 * 任意包名
     * ~ 第三个 * 代表任意方法.
     * ~ 第四个 * 定义在web包或者子包
     * ~ 第五个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     */
    @Pointcut("execution(* com.northbrain..*.*(..))")
    public void invokeLog(){}

    /**
     * 方法：在被调用方法的前后打印时间及耗时
     * @param proceedingJoinPoint 处理连接点
     * @return 被调用方法的处理结果
     * @throws Throwable 异常
     */
    @Around("invokeLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        long startTime = System.currentTimeMillis();

        logger.info("开始调用：" + proceedingJoinPoint.toString());

        Object result = proceedingJoinPoint.proceed();

        long finishTime = System.currentTimeMillis();
        logger.info("结束调用：" + proceedingJoinPoint.toString() + "，总计耗时：" + (finishTime-startTime) + "ms");
        System.out.println("结束调用：" + proceedingJoinPoint.toString() + "，总计耗时：" + (finishTime-startTime) + "ms");
        return result;
    }
}
