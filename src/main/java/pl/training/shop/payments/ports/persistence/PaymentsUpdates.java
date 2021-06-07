package pl.training.shop.payments.ports.persistence;

import pl.training.shop.payments.domain.Payment;

public interface PaymentsUpdates {

    Payment save(Payment payment);

}
