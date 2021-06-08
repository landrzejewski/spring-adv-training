package pl.training.blog;

import lombok.extern.java.Log;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.training.blog.commons.money.LocalMoney;
import pl.training.blog.payments.*;

import static java.util.Collections.emptyMap;

@Log
public class Application {

    private static final String BASE_PACKAGE = "pl.training.blog";

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BASE_PACKAGE)) {
            var payments = context.getBean(Payments.class);
            var paymentRequest = new PaymentRequest(LocalMoney.of(1_000), emptyMap());
            var payment = payments.process(paymentRequest);
            log.info(payment.toString());
        }
    }

}
