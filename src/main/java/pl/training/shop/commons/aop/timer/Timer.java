package pl.training.shop.commons.aop.timer;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Component
@Log
public class Timer {

    @Around("@annotation(pl.training.shop.commons.aop.timer.MeasureTime)")
    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        var startTime = System.nanoTime();
        var result = proceedingJoinPoint.proceed();
        var totalTime = System.nanoTime() - startTime;
        log.info(String.format("%s executed in %d ns", proceedingJoinPoint.getSignature(), totalTime));
        return  result;
    }

}
