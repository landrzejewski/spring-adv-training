package pl.training.shop.payments.adapters.logging;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import pl.training.shop.payments.domain.Payment;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Aspect
@RequiredArgsConstructor
public class FilePaymentsLogger implements Ordered {

    private final Path path;

    @AfterReturning(value = "@annotation(pl.training.shop.payments.application.PaymentProcess)", returning = "payment")
    @SneakyThrows
    public void log(Payment payment) {
        Files.writeString(path, payment.toString() + "\n", StandardOpenOption.APPEND);
    }

    @Override
    public int getOrder() {
        return 10_000;
    }

}
