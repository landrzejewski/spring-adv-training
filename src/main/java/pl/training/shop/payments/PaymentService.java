package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.training.shop.commons.retry.Retry;
import pl.training.shop.commons.time.TimeProvider;

@Service
@RequiredArgsConstructor
public class PaymentService implements Payments {

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentRepository paymentRepository;
    private final TimeProvider timeProvider;

    //@Retry(attempts = 4)
    //@LogExecutionTime
    //@LogPayments
    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var payment = createPayment(paymentRequest);
        return paymentRepository.save(payment);
    }

    private Payment createPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .id("01")
                //.id(paymentIdGenerator.getNext())
                .value(paymentRequest.getValue())
                .properties(paymentRequest.getProperties())
                .timestamp(timeProvider.getTimestamp())
                .status(PaymentStatus.STARTED)
                .build();
    }

    @Override
    public Payment findById(String id) {
        return paymentRepository.findById(id)
                .orElseThrow(PaymentNotFoundException::new);
    }

}
