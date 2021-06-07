package pl.training.shop.payments.ports.usecases;

import pl.training.shop.payments.domain.Payment;

public interface ProcessPaymentUseCase {

    Payment process(PaymentRequest paymentRequest);

}
