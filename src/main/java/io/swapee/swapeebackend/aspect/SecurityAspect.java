package io.swapee.swapeebackend.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * @author Minoltan Issack on 12/24/2022
 */
@Aspect
@Component
public class SecurityAspect {
    private Logger logger = Logger.getLogger(SecurityAspect.class.getName());

    @Autowired
    HttpServletRequest request;

    @Around(value = "@annotation(ToLog)")
    public Object secure(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object [] arguments = joinPoint.getArgs();
        logger.info("Internal Request: "+ methodName + " | URI: (" +request.getRequestURI()+") | "  +
                " with parameters " + Arrays.asList(arguments));
        Object returnedValue = joinPoint.proceed();
        logger.info("Internal Response: " + returnedValue);
        return returnedValue;
    }

    // TODO try hiding sensitive details in the logs


}




