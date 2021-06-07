package pl.training.shop.commons.aop.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.*;
import java.lang.reflect.InvocationTargetException;

@Component
@RequiredArgsConstructor
public class ValidatorService {

    private final Validator validator;

    public <O, E extends RuntimeException> void validate(O object, Class<E> exceptionType) throws E {
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
