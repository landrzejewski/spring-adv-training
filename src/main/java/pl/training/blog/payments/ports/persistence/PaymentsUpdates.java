package pl.training.blog.payments.ports.persistence;

import pl.training.blog.payments.domain.Payment;

public interface PaymentsUpdates {

    Payment save(Payment payment);

}
