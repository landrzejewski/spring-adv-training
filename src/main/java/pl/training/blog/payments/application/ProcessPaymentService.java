package pl.training.blog.payments.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import pl.training.blog.payments.domain.Payment;
import pl.training.blog.payments.domain.PaymentStatus;
import pl.training.blog.payments.ports.payments.PaymentsProcessor;
import pl.training.blog.payments.ports.persistence.PaymentsUpdates;
import pl.training.blog.payments.ports.time.TimeProvider;
import pl.training.blog.payments.ports.usecases.ProcessPaymentUseCase;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
@Log
public class ProcessPaymentService implements ProcessPaymentUseCase {

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentsUpdates paymentsUpdates;
    private final PaymentsProcessor paymentsProcessor;
    private final TimeProvider timeProvider;

    @PaymentProcess
    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var payment = createPayment(paymentRequest);
        paymentsProcessor.process(payment)
                .subscribe(result -> log.info("Updating payment: " + result), error -> log.warning(error.getMessage()), () -> log.info("Completed"));
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
