package pl.training.blog.payments.ports.persistence;

import pl.training.blog.payments.domain.Payment;

import java.util.Optional;

public interface PaymentsQueries {

    Optional<Payment> findById(String id);

}
