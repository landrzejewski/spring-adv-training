package pl.training.shop.payments;

import java.util.Optional;

public interface PaymentsRepository {

    Payment save(Payment payment);

    Optional<Payment> findById(String id);

}
