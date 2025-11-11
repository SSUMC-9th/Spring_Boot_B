package com.ssu.umc9th2.spring_boot_b.common.log.discord;

import com.ssu.umc9th2.spring_boot_b.common.exception.GeneralException;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class DiscordLoggerAop {

    private final DiscordAlarmSender discordAlarmSender;

    public DiscordLoggerAop(DiscordAlarmSender discordAlarmSender) {
        this.discordAlarmSender = discordAlarmSender;
    }

    @Pointcut("execution(* com.ssu.umc9th2.spring_boot_b.common.exception.GeneralExceptionAdvice.handleGeneralException(..))")
    public void generalExceptionErrorLoggerExecute() {}

    @Pointcut("execution(* com.ssu.umc9th2.spring_boot_b.common.exception.GeneralExceptionAdvice.handleException(..))")
    public void serverExceptionErrorLoggerExecute() {}

    @Before("generalExceptionErrorLoggerExecute()")
    public void generalExceptionLogging(JoinPoint joinPoint) {
        HttpServletRequest request = getCurrentRequest();
        GeneralException exception = (GeneralException) joinPoint.getArgs()[0];

        if (exception.getErrorStatus().getHttpStatus() == HttpStatus.INTERNAL_SERVER_ERROR) {
            discordAlarmSender.sendDiscordAlarm(exception, request);
        }
    }

    @Before("serverExceptionErrorLoggerExecute()")
    public void serverExceptionLogging(JoinPoint joinPoint) {
        HttpServletRequest request = getCurrentRequest();
        Exception exception = (Exception) joinPoint.getArgs()[0];

        discordAlarmSender.sendDiscordAlarm(exception, request);
    }

    private HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
}
