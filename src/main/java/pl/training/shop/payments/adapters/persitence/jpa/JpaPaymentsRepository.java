package pl.training.shop.payments.adapters.persitence.jpa;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import pl.training.shop.payments.domain.Payment;
import pl.training.shop.payments.ports.persistence.PaymentsQueries;
import pl.training.shop.payments.ports.persistence.PaymentsUpdates;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaPaymentsRepository implements PaymentsQueries, PaymentsUpdates {

    private final JpaModelMapper modelMapper;

    @PersistenceContext
    @Setter
    private EntityManager entityManager;

    @Override
    public Payment save(Payment payment) {
        var entity = modelMapper.toEntity(payment);
        entityManager.persist(entity);
        return modelMapper.toDomain(entity);
    }

    @Override
    public Optional<Payment> findById(String id) {
        var entity = entityManager.find(PaymentEntity.class, id);
        return Optional.ofNullable(entity).map(modelMapper::toDomain);
    }

}
