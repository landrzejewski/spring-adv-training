package pl.training.shop.commons.aop.validator;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
public class ModelValidator {

    private final ValidatorService validatorService;

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
                validatorService.validate(arguments[index], validateAnnotation.get().exception());
            }
        }
    }

    private Optional<Validate> getValidateAnnotation(Annotation[] annotations) {
        return Arrays.stream(annotations)
                .filter(annotation -> annotation instanceof Validate)
                .map(annotation -> (Validate) annotation)
                .findFirst();
    }

}
