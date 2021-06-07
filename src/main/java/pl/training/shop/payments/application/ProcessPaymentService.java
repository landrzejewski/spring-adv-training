package pl.training.shop.payments.application;

import lombok.RequiredArgsConstructor;
import pl.training.shop.payments.domain.Payment;
import pl.training.shop.payments.domain.PaymentStatus;
import pl.training.shop.payments.ports.persistence.PaymentsUpdates;
import pl.training.shop.payments.ports.providers.TimeProvider;
import pl.training.shop.payments.ports.usecases.PaymentProcess;
import pl.training.shop.payments.ports.usecases.PaymentRequest;
import pl.training.shop.payments.ports.usecases.ProcessPaymentUseCase;

import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
public class ProcessPaymentService implements ProcessPaymentUseCase {

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentsUpdates paymentsUpdates;
    private final TimeProvider timeProvider;

    @PaymentProcess
    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var payment = createPayment(paymentRequest);
        return paymentsUpdates.save(payment);
    }

    private Payment createPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .id(paymentIdGenerator.getNext())
                .value(paymentRequest.getValue())
                .properties(paymentRequest.getProperties())
                .timestamp(timeProvider.getTimestamp())
                .status(PaymentStatus.STARTED)
                .build();
    }

}
