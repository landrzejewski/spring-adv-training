package pl.training.blog;

import lombok.extern.java.Log;
import pl.training.blog.commons.money.LocalMoney;
import pl.training.blog.commons.time.SystemTimeProvider;
import pl.training.blog.payments.*;

import static java.util.Collections.emptyMap;

@Log
public class Application {

    public static void main(String[] args) {
        var paymentService = new PaymentsService(new UUIDPaymentIdGenerator(), new InMemoryPaymentsRepository(), new SystemTimeProvider());
        var payments = new PaymentsLoggingProxy(paymentService);
        var paymentRequest = new PaymentRequest(LocalMoney.of(1_000), emptyMap());
        var payment = payments.process(paymentRequest);
        log.info(payment.toString());
    }

}
