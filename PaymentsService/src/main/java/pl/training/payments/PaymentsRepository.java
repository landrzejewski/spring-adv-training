package pl.training.payments;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PaymentsRepository extends ReactiveMongoRepository<PaymentDocument, String> {
}
