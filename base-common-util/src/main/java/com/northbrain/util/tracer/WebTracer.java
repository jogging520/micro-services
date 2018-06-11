package com.northbrain.util.tracer;

import java.util.Arrays;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

import com.northbrain.util.model.Constants;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 类名：TracerUtil
 * 用途：web日志跟踪器，利用spring的aop打印web servlet日志。
 * @author Jiakun
 * @version 1.0
 */
@Aspect
@Component
@Order(0)
public class WebTracer {
    private final Logger logger = LoggerFactory.getLogger(WebTracer.class);
    private ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<>();

    @Pointcut("execution(* com.northbrain..*Controller.*(..)) && " +
            "(@annotation(org.springframework.web.bind.annotation.GetMapping)) ||" +
            "(@annotation(org.springframework.web.bind.annotation.PostMapping)) ||" +
            "(@annotation(org.springframework.web.bind.annotation.PutMapping)) ||" +
            "(@annotation(org.springframework.web.bind.annotation.DeleteMapping))")
    public void invokeLog(){}

    @Before("invokeLog()")
    public void doBefore(JoinPoint joinPoint) {
        //设置请求时间
        startTimeThreadLocal.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();


        // 记录详细请求内容
        logger.info(Constants.UTIL_HTTP_REQUEST_URL + httpServletRequest.getRequestURL().toString());
        logger.info(Constants.UTIL_HTTP_REQUEST_METHOD + httpServletRequest.getMethod());
        logger.info(Constants.UTIL_HTTP_REQUEST_IP + httpServletRequest.getRemoteAddr());
        logger.info(Constants.UTIL_HTTP_REQUEST_CLASS_METHOD + joinPoint.getSignature().getDeclaringTypeName()
                + Constants.UTIL_HTTP_REQUEST_CLASS_METHOD_SEPARATOR
                + joinPoint.getSignature().getName());
        logger.info(Constants.UTIL_HTTP_REQUEST_PARAMS + Arrays.toString(joinPoint.getArgs()));
        //获取所有参数：
        Enumeration<String> enumeration = httpServletRequest.getParameterNames();
        while(enumeration.hasMoreElements()) {
            String paraName = enumeration.nextElement();
            logger.info(httpServletRequest.getParameter(paraName));
        }
    }

    @AfterReturning(returning = "response", pointcut = "invokeLog()")
    public void  doAfterReturning(Object response) throws Throwable {
        // 处理完请求，返回内容
        long startTime = startTimeThreadLocal.get();
        long finishTime = System.currentTimeMillis();

        logger.info(Constants.UTIL_HTTP_RESPONSE_BODY + response);
        logger.info(Constants.UTIL_HTTP_REQUEST_RESPONSE_COST + String.valueOf(finishTime-startTime));
    }
}
