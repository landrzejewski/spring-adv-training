package pl.training.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Repository
@RequiredArgsConstructor
public class PaymentsRepositoryAdapter {

    private final PaymentsRepository paymentsRepository;
    private final PaymentMapper paymentMapper;

    public Mono<Payment> save(Payment payment) {
        var document = paymentMapper.toDocument(payment);
        document.setTimestamp(Instant.now());
        return paymentsRepository.save(document)
                .map(paymentMapper::toDomain);
    }

}
