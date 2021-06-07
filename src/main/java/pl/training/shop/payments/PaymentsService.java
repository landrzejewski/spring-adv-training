package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import pl.training.shop.commons.time.TimeProvider;

@RequiredArgsConstructor
public class PaymentsService implements Payments {

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentsRepository paymentsRepository;
    private final TimeProvider timeProvider;

    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var payment = createPayment(paymentRequest);
        return paymentsRepository.save(payment);
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

    @Override
    public Payment findById(String id) {
        return paymentsRepository.findById(id)
                .orElseThrow(PaymentNotFoundException::new);
    }

}
