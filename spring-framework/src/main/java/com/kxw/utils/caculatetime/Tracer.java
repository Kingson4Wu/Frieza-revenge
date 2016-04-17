package com.kxw.utils.caculatetime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
/**
 * an around advice used for logging duration and exception if thrown during a method invocation
 * @author pinkdahlia
 */
@Aspect
public class Tracer{
    @Around("@annotation(com.abc.mlisting.performance.annotation.Traced) or @within(com.abc.mlisting.performance.annotation.Traced)")
    public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getClass());
        logger.info("Invoking {} started!", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            Object result = joinPoint.proceed();
            stopWatch.stop();
            logger.info("Invoking {} finished! {} milliseconds elapsed!", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(), stopWatch.getTotalTimeMillis());
            return result;
        }catch(Exception e){
            logger.warn("Exception thrown during the invocation of {}, the cause is {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(), e);
            throw e;
        }
    }

}