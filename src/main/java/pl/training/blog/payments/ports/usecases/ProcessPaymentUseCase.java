package pl.training.blog.payments.ports.usecases;

import pl.training.blog.payments.application.PaymentRequest;
import pl.training.blog.payments.domain.Payment;

public interface ProcessPaymentUseCase {

    Payment process(PaymentRequest paymentRequest);

}
