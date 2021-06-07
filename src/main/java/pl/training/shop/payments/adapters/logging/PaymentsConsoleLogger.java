
package pl.training.shop.payments.adapters.logging;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pl.training.shop.payments.ports.usecases.PaymentRequest;
import pl.training.shop.payments.domain.Payment;

@Order(100_000)
@Aspect
@Component
@Log
public class PaymentsConsoleLogger {

    @Pointcut("@annotation(pl.training.shop.payments.ports.usecases.PaymentProcess)")
    public void logPayments() {
    }

    @Before("logPayments() && args(paymentRequest)")
    public void beforePayment(JoinPoint joinPoint, PaymentRequest paymentRequest) {
        log.info("New payment request: " + paymentRequest);
    }

    @After("logPayments()")
    public void afterPayment() {
        log.info("After payment");
    }

    @AfterReturning(value = "logPayments()", returning = "payment")
    public void log(Payment payment) {
        log.info("Payment created: " + payment);
    }

    @AfterThrowing(value = "logPayments()", throwing = "exception")
    public void log(RuntimeException exception) {
        log.info("Payment exception: " + exception);
    }

}
