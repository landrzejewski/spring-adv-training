package pl.training.shop.payments.adapters.persitence.jpa;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.training.shop.payments.domain.Payment;
import pl.training.shop.payments.ports.persistence.PaymentsQueries;
import pl.training.shop.payments.ports.persistence.PaymentsUpdates;

import java.util.Optional;

@Primary
@Repository
@RequiredArgsConstructor
public class SpringDataJpaPaymentsRepository implements PaymentsQueries, PaymentsUpdates {

    private final SpringDataPaymentsRepository paymentsRepository;
    private final JpaPersistenceMapper jpaPersistenceMapper;

    @Override
    public Payment save(Payment payment) {
        var entity = jpaPersistenceMapper.toEntity(payment);
        return jpaPersistenceMapper.toDomain(paymentsRepository.save(entity));
    }

    @Override
    public Optional<Payment> findById(String id) {
        var entity = paymentsRepository.findById(id);
        if (entity.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(jpaPersistenceMapper.toDomain(entity.get()));
    }

}
