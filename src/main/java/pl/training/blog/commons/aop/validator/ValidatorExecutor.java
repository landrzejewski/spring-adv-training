package pl.training.blog.commons.aop.validator;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.validation.Validator;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
public class ValidatorExecutor {

    private final Validator validator;

    @Before("execution(* *(@Validate (*)))")
    @SneakyThrows
    public void validate(JoinPoint joinPoint) {
        var signature = (MethodSignature) joinPoint.getSignature();
        var methodName = signature.getMethod().getName();
        var parameterTypes = signature.getMethod().getParameterTypes();
        var annotations = joinPoint.getTarget().getClass().getMethod(methodName, parameterTypes).getParameterAnnotations();
        var arguments = joinPoint.getArgs();

        for (int index = 0; index < arguments.length; index++) {
            var validateAnnotation = getValidateAnnotation(annotations[index]);
            if (validateAnnotation.isPresent()) {
                validate(arguments[index], validateAnnotation.get().exception());
            }
        }
    }

    private Optional<Validate> getValidateAnnotation(Annotation[] annotations) {
        return Arrays.stream(annotations)
                .filter(annotation -> annotation instanceof Validate)
                .map(annotation -> (Validate) annotation)
                .findFirst();
    }

    private <O, E extends RuntimeException> void validate(O object, Class<E> exceptionType) throws E {
        var violations = validator.validate(object);
        if (!violations.isEmpty()) {
            try {
                var exception = exceptionType.getDeclaredConstructor();
                throw exception.newInstance();
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new IllegalArgumentException();
            }
        }
    }

}
