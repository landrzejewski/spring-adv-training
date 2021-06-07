package pl.training.shop.payments;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class InMemoryPaymentRepository implements PaymentRepository {

    private final Set<Payment> payments = new HashSet<>();

    public Payment save(Payment payment) {
        payments.add(payment);
        return payment;
    }

    public Optional<Payment> findById(String id) {
        return payments.stream()
                .filter(payment -> payment.hasId(id))
                .findFirst();
    }

}
