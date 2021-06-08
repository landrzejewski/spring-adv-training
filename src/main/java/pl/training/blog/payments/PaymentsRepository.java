package pl.training.blog.payments;

import java.util.Optional;

public interface PaymentsRepository {

    Payment save(Payment payment);

    Optional<Payment> findById(String id);

}
