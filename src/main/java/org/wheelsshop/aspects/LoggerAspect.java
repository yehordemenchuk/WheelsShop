package org.wheelsshop.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Slf4j
@Component
public class LoggerAspect {
    @Around("execution(* org.wheelsshop..*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("{} starts execution", joinPoint.getSignature().toShortString());

        Instant start = Instant.now();

        Object proceedingRes = joinPoint.proceed();

        Instant end = Instant.now();

        long duration = Duration.between(start, end).toMillis();

        log.info("duration: {} ms", duration);

        log.info("{} ends execution", joinPoint.getSignature().toShortString());

        return proceedingRes;
    }

    @AfterThrowing(value = "execution(* org.wheelsshop..*.*(..))", throwing = "ex")
    public void log(JoinPoint joinPoint, Throwable ex) {
        log.error("{} exception: {}", joinPoint.getSignature().toShortString(), ex.getMessage());
    }

    @AfterReturning(value = "execution(* org.wheelsshop..*.*(..))", returning = "retVal")
    public void log(JoinPoint joinPoint, Object retVal) {
        log.info("{} returns {}", joinPoint.getSignature().toShortString(), retVal);
    }
}
