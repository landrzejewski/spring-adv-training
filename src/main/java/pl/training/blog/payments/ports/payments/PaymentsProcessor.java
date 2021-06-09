package pl.training.blog.payments.ports.payments;

import pl.training.blog.payments.domain.Payment;
import reactor.core.publisher.Mono;

public interface PaymentsProcessor {

    Mono<Payment> process(Payment payment);

}
