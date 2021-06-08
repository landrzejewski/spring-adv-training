package pl.training.blog.payments.application;

import lombok.RequiredArgsConstructor;
import pl.training.blog.payments.domain.Payment;
import pl.training.blog.payments.domain.PaymentStatus;
import pl.training.blog.payments.ports.persistence.PaymentsUpdates;
import pl.training.blog.payments.ports.time.TimeProvider;
import pl.training.blog.payments.ports.usecases.ProcessPaymentUseCase;

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
