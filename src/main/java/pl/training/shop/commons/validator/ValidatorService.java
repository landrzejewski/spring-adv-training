package pl.training.shop.commons.validator;

import lombok.RequiredArgsConstructor;

import javax.validation.*;
import java.lang.reflect.InvocationTargetException;

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
