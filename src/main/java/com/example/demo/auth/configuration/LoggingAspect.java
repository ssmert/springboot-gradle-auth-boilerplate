package com.example.demo.auth.configuration;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Configuration
public class LoggingAspect {
        @Around("execution(* com.example.demo.core.api.*Controller.*(..))"
                + " && !execution(* com.example.demo.auth.api.AuthController.*(..))"
                + " || execution(* com.example.demo..application..*Service.*(..))"
                + " || execution(* com.example.demo..infra.repository.*Impl.*(..))")
//    @Around("execution(* com.example.*.*.api.*Controller.*(..))")
    public Object logTimeMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        String type = joinPoint.getSignature().getDeclaringTypeName();
        String name = "";

        stopWatch.start();
        Object retVal = joinPoint.proceed();
        stopWatch.stop();

        if (type.contains("Controller")) {
            name = "Controller  \t:  ";
        }
        else if (type.contains("Service")) {
            name = "Service     \t:  ";
        }
        else if (type.contains("Dao")) {
            name = "Dao         \t:  ";
        }
        else if (type.contains("Repository")) {
            name = "Repository  \t:  ";
        }

        log.info(String.format("%s[%s.%s] Execute Time : %.3f(sec)", name, type, joinPoint.getSignature().getName(), stopWatch.getTotalTimeMillis() / 1000.0));

        return retVal;
    }
}
