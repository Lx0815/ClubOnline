package com.sgqn.clubonline.web.aspect;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

/**
 * @description:
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-02-26 16:46:16
 * @modify:
 */
@Component
@Aspect
@Slf4j
public class GlobalRequestLoggerAspect {

    private static final String LOG_SPLIT_LINE = ">>><<<>>><<<>>><<<>>><<<>>><<<>>><<<>>><<<>>><<<>>><<<>>><<<>>><<<>>><<<>>><<<>>><<<>>><<<>>><<<>>><<<>>><<<>>><<<>>><<<";

    @Pointcut("execution(* com.sgqn.clubonline.web.controller.*.*(..)) *")
    public void allControllerMethod() {
    }

    @Around("allControllerMethod()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info(getRequestInfo(pjp));
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long time = System.currentTimeMillis() - start;
        log.info(appendResponseInfo(result, time));
        return result;
    }

    private String appendResponseInfo(Object result, long time) {
        return result +
                "请求耗时：" + time + " ms" + "\n" +
                LOG_SPLIT_LINE;
    }

    private String getRequestInfo(ProceedingJoinPoint pjp) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return "\n" + LOG_SPLIT_LINE + "\n" +
                "RequestId: " + UUID.randomUUID() + "\n" +
                "IP: " + getIpAddr(request) + "\n" +
                "Time: " + LocalDateTime.now() + "\n" +
                "URL: " + request.getRequestURL() + "\n" +
                "Args: " + Arrays.toString(pjp.getArgs()) + "\n";
    }

    /**
     * 获取 IP 地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip))
            return ip;
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            int index = ip.indexOf(',');
            if (index != -1)
                return ip.substring(0, index);
            else
                return ip;
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("Proxy-Client-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("WL-Proxy-Client-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("HTTP_CLIENT_IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getRemoteAddr();
        return ip;
    }

}
