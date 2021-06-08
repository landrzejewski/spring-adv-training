package pl.training.blog.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import pl.training.blog.commons.time.TimeProvider;

@Transactional
@Service
@RequiredArgsConstructor
public class PaymentsService implements Payments {

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentsRepository paymentsRepository;
    private final TimeProvider timeProvider;
    //private final TransactionTemplate transactionTemplate;

    //@Retry(attempts = 4)
    //@LogExecutionTime
    //@LogPayments
    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var payment = createPayment(paymentRequest);
        return paymentsRepository.save(payment);
        //return transactionTemplate.execute(tx -> paymentsRepository.save(payment));
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
