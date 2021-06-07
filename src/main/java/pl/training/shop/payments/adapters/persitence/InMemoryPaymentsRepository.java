package pl.training.shop.payments.adapters.persitence;

import org.springframework.stereotype.Repository;
import pl.training.shop.commons.validator.Validate;
import pl.training.shop.payments.domain.Payment;
import pl.training.shop.payments.ports.persistence.PaymentsQueries;
import pl.training.shop.payments.ports.persistence.PaymentsUpdates;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class InMemoryPaymentsRepository implements PaymentsQueries, PaymentsUpdates {

    private final Set<Payment> payments = new HashSet<>();

    public Payment save(@Validate Payment payment) {
        payments.add(payment);
        return payment;
    }

    public Optional<Payment> findById(String id) {
        return payments.stream()
                .filter(payment -> payment.hasId(id))
                .findFirst();
    }

}
