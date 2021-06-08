package pl.training.blog.payments;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

//@Order(10_000)
@Aspect
@RequiredArgsConstructor
public class FilePaymentsLogger implements Ordered {

    private final Path path;

    @AfterReturning(value = "@annotation(pl.training.blog.payments.PaymentProcess)", returning = "payment")
    @SneakyThrows
    public void log(Payment payment) {
        Files.writeString(path, payment.toString() + "\n", StandardOpenOption.APPEND);
    }

    @Override
    public int getOrder() {
        return 10_000;
    }

}
