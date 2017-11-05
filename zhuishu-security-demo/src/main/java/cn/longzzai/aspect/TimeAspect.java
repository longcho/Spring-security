package cn.longzzai.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author longcho
 * 2017-10-16-16:46
 */
@Component
@Aspect
public class TimeAspect {

    @Around("execution(* cn.longzzai.controller.UserController.*(..))")
    public Object handleUserControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("aspect  start");
        long date = new Date().getTime();
        Object proceed = joinPoint.proceed();
        System.out.println("aspect方法耗时：" +(new Date().getTime()-date)+"ms");
        System.out.println("aspect  end");
        return proceed;
    }
}
