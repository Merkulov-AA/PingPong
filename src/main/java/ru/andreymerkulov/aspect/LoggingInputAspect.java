package ru.andreymerkulov.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component
@Aspect
@Slf4j
public class LoggingInputAspect {

    @Pointcut("within(ru.andreymerkulov..*)")
    public void projectMethods() {}

    @Before("projectMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info(joinPoint.getSignature().toString());
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            log.info(arg.toString());
        }
    }
}
