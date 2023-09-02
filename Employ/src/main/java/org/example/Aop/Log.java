package org.example.Aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: He Zhiyang
 * @Date: 2023/04/19/21:25
 * @Description:
 */
@Component
@Aspect
public class Log {
    @Before("execution(public * org.example.Service.*.*(..))")
    public void before(JoinPoint joinPoint) {
        String methodName=joinPoint.getSignature().getName();
        String params= String.valueOf(joinPoint.getArgs());
        System.out.println( methodName+"方法参数是"+params);
    }
    @AfterReturning(value = "execution(public * org.example.Service.*.*(..))",returning = "result")
    public void after(JoinPoint joinPoint,Object result) {
        String methodName=joinPoint.getSignature().getName();
        System.out.println(methodName+"方法结束"+result);

    }
}


