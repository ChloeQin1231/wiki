//package com.chloe.wiki.aspect;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ser.FilterProvider;
//import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
//import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
//import com.chloe.wiki.util.RequestContext;
//import com.chloe.wiki.util.SnowFlake;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.slf4j.MDC;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.multipart.MultipartFile;
//
//import jakarta.annotation.Resource;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.List;
//
//@Aspect
//@Component
//public class LogAspect {
//
//    private final static Logger LOG = LoggerFactory.getLogger(LogAspect.class);
//    private final ObjectMapper objectMapper;
//
//    public LogAspect() {
//        this.objectMapper = new ObjectMapper();
//    }
//
//    /** Define a pointcut */
//    @Pointcut("execution(public * com.chloe.*.controller..*Controller.*(..))")
//    public void controllerPointcut() {}
//
//    @Resource
//    private SnowFlake snowFlake;
//
//    @Before("controllerPointcut()")
//    public void doBefore(JoinPoint joinPoint) throws Throwable {
//        // Add log trace ID
//        MDC.put("LOG_ID", String.valueOf(snowFlake.nextId()));
//
//        // Start printing request logs
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        Signature signature = joinPoint.getSignature();
//        String name = signature.getName();
//
//        // Print request information
//        LOG.info("------------- Start -------------");
//        LOG.info("Request URL: {} {}", request.getRequestURL().toString(), request.getMethod());
//        LOG.info("Class Method: {}.{}", signature.getDeclaringTypeName(), name);
//        LOG.info("Remote Address: {}", request.getRemoteAddr());
//
//        RequestContext.setRemoteAddr(getRemoteIp(request));
//
//        // Print request parameters
//        Object[] args = joinPoint.getArgs();
//        List<Object> arguments = new ArrayList<>();
//        for (Object arg : args) {
//            if (!(arg instanceof ServletRequest) &&
//                    !(arg instanceof ServletResponse) &&
//                    !(arg instanceof MultipartFile)) {
//                arguments.add(arg);
//            }
//        }
//
//        // Configure property filter for sensitive fields
//        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
//                .serializeAllExcept("password", "file");
//        FilterProvider filters = new SimpleFilterProvider()
//                .addFilter("sensitiveFilter", filter);
//
//        try {
//            LOG.info("Request Parameters: {}",
//                    objectMapper.writer(filters).writeValueAsString(arguments));
//        } catch (JsonProcessingException e) {
//            LOG.error("Failed to serialize request parameters", e);
//        }
//    }
//
//    @Around("controllerPointcut()")
//    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        long startTime = System.currentTimeMillis();
//        Object result = proceedingJoinPoint.proceed();
//
//        // Configure property filter for response
//        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
//                .serializeAllExcept("password", "file");
//        FilterProvider filters = new SimpleFilterProvider()
//                .addFilter("sensitiveFilter", filter);
//
//        try {
//            LOG.info("Response Result: {}",
//                    objectMapper.writer(filters).writeValueAsString(result));
//        } catch (JsonProcessingException e) {
//            LOG.error("Failed to serialize response", e);
//        }
//
//        LOG.info("------------- End Time Elapsed: {} ms -------------",
//                System.currentTimeMillis() - startTime);
//        return result;
//    }
//
//    /**
//     * Get real IP address when using nginx as reverse proxy
//     */
//    public String getRemoteIp(HttpServletRequest request) {
//        String ip = request.getHeader("x-forwarded-for");
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//        return ip;
//    }
//}


