package pl.training.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Service
@RequiredArgsConstructor
public class PaymentsService {

    private final Sinks.Many<Payment> payments = Sinks.many().replay().all();
    private final PaymentsRepositoryAdapter paymentsRepository;

    public Flux<Payment> getPayments() {
        return payments.asFlux();
    }

    public Mono<Payment> process(Mono<Payment> payment) {
        return payment
                .map(this::processedPayment)
                .flatMap(paymentsRepository::save)
                .doOnNext(payments::tryEmitNext);
    }

    private Payment processedPayment(Payment payment) {
        var result = new Payment();
        result.setProperties(payment.getProperties());
        result.setValue(payment.getValue());
        result.setRequestId(payment.getRequestId());
        result.setStatus("COMPLETED");
        return result;
    }

}
