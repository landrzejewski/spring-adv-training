
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
public class ConsolePaymentsLogger {

    @Pointcut("@annotation(pl.training.shop.payments.ports.usecases.PaymentProcess)")
    public void processPaymentRequest() {
    }

    @Before("processPaymentRequest() && args(paymentRequest)")
    public void beforePayment(JoinPoint joinPoint, PaymentRequest paymentRequest) {
        log.info("New payment request: " + paymentRequest);
    }

    @After("processPaymentRequest()")
    public void afterPayment() {
        log.info("After payment");
    }

    @AfterReturning(value = "processPaymentRequest()", returning = "payment")
    public void log(Payment payment) {
        log.info("Payment created: " + payment);
    }

    @AfterThrowing(value = "processPaymentRequest()", throwing = "exception")
    public void log(RuntimeException exception) {
        log.info("Payment exception: " + exception);
    }

}
