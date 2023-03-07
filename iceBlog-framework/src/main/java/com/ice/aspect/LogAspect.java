package com.ice.aspect;

import com.alibaba.fastjson.JSON;
import com.ice.annotation.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @Package:com.ice.aspect
 * @ClassName:LogAspect
 * @Auther:iceee
 * @Date:2022/3/20
 * @Description:
 */

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(com.ice.annotation.SystemLog)")
    public void pt(){

    }



    @Around("pt()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //ProceedingJoinPoint被增强方法的信息封装的对象
        Object ret;
        try {
            handleBefore(joinPoint);
            ret = joinPoint.proceed();
            handleAfter();
        } finally {
            log.info("=======End=======" + System.lineSeparator());
        }

        return ret;

    }

    private void handleAfter() {


    }



    private void handleBefore(ProceedingJoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //获取被增强方法上的注解对象 (的信息)
        SystemLog systemLog = getSystemLog(joinPoint);
        log.info("=======Start=======");
        // 打印请求 URL
        log.info("URL            : {}",request.getRequestURL());
        // 打印描述信息
        log.info("BusinessName   : {}", systemLog.busnessName());
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(),((MethodSignature) joinPoint.getSignature()).getName());
        // 打印请求的 IP
        log.info("IP             : {}",request.getRemoteHost());
        // 打印请求入参
        log.info("Request Args   : {}", JSON.toJSON(joinPoint.getArgs()));
    }


    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        SystemLog systemLog = methodSignature.getMethod().getAnnotation(SystemLog.class);
        return systemLog;
    }
}
