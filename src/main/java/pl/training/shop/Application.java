package pl.training.shop;

import lombok.extern.java.Log;
import pl.training.shop.commons.money.LocalMoney;
import pl.training.shop.commons.time.SystemTimeProvider;
import pl.training.shop.payments.*;

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
