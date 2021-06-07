package pl.training.shop.payments.adapters.persitence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPaymentsRepository extends JpaRepository<PaymentEntity, String> {
}
