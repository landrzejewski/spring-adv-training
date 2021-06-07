
package pl.training.shop.payments;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(100_000)
@Aspect
@Component
@Log
public class ConsolePaymentsLogger {

    @Pointcut("@annotation(PaymentProcess)")
    public void paymentProcess() {
    }

    @Before("paymentProcess() && args(paymentRequest)")
    public void beforePayment(JoinPoint joinPoint, PaymentRequest paymentRequest) {
        log.info("New payment request: " + paymentRequest);
    }

    @After("paymentProcess()")
    public void afterPayment() {
        log.info("After payment");
    }

    @AfterReturning(value = "paymentProcess()", returning = "payment")
    public void log(Payment payment) {
        log.info("Payment created: " + payment);
    }

    @AfterThrowing(value = "paymentProcess()", throwing = "exception")
    public void log(RuntimeException exception) {
        log.info("Payment exception: " + exception);
    }

}
