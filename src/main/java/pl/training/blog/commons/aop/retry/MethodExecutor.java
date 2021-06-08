package pl.training.blog.commons.aop.retry;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log
public class MethodExecutor {

    @Around("@annotation(Retry)")
    public Object execute(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        var method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        var annotation = AnnotationUtils.findAnnotation(method, Retry.class);
        if (annotation == null) {
            throw new IllegalStateException();
        }
        var attempts = annotation.attempts();
        var currentAttempt = 0;
        Throwable throwable;
        do {
            currentAttempt++;
            log.info(String.format("%s execution attempt %d", proceedingJoinPoint.getSignature(), currentAttempt));
            try {
                return proceedingJoinPoint.proceed();
            } catch (Throwable t) {
                throwable = t;
            }
        } while (currentAttempt < attempts);
        throw throwable;
    }

}
