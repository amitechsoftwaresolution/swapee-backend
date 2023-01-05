package io.swapee.swapeebackend.aspect;

import io.swapee.swapeebackend.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * @author Minoltan Issack on 12/20/2022
 */


@Aspect
@Configuration
@Component
public class LoggingAspect {
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Autowired
    HttpServletRequest request;

    @Pointcut("execution(* io.swapee.swapeebackend.adapter.inbound.*.*(..))")
    public void loggingPointCut(){}

  /*  @Before("loggingPointCut()")
    public void before(JoinPoint joinPoint){
        logger.info("Before Method Invoked::"+joinPoint.getSignature());
    }*/

    /*@After("loggingPointCut()")
    public void after(JoinPoint joinPoint){
        logger.info("After Method Invoked::"+joinPoint.getSignature());
    }*/

   /* @Around("loggingPointCut()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object [] arguments = joinPoint.getArgs();
        logger.info("Method " + methodName +
                " with parameters " + Arrays.asList(arguments) +
                " will execute");
        Object returnedByMethod = joinPoint.proceed();
        logger.info("Method executed and returned " + returnedByMethod);
        return returnedByMethod;
    }*/

    @Around("loggingPointCut()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().getName();
        Object [] arguments = joinPoint.getArgs();
        logger.info("Request: "+ methodName + " | URI: (" +request.getRequestURI()+") | "  +
                " with parameters " + Arrays.asList(arguments));
        Object returnedByMethod = joinPoint.proceed();
        logger.info("Response: " + returnedByMethod);
        return returnedByMethod;
    }
    /*@AfterReturning(value = "@annotation(ToLog)",
            returning = "returnedValue")
    public void log(Object returnedValue) {
        logger.info("Method executed and returned " + returnedValue);
    }*/

    // Todo: Solve this issue
   /* @Around("loggingPointCut()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object [] arguments = joinPoint.getArgs();
        logger.info("Method " + methodName +
                " with parameters " + Arrays.asList(arguments) +
                " will execute");
        User user = new User();
        user.setName("Some other text!");
        Object [] newArguments = {user};
        Object returnedByMethod = joinPoint.proceed(newArguments);
        logger.info("Method executed and returned " + returnedByMethod);
        return "FAILED";
    }*/



}