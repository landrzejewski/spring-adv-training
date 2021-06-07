package pl.training.shop.payments.ports.persistence;

import pl.training.shop.payments.domain.Payment;

import java.util.Optional;

public interface PaymentsQueries {

    Optional<Payment> findById(String id);

}
