package pl.training.shop.payments.adapters.persitence.jpa;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.training.shop.payments.domain.Payment;
import pl.training.shop.payments.ports.persistence.PaymentsQueries;
import pl.training.shop.payments.ports.persistence.PaymentsUpdates;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Primary
@Repository
public class JpaPaymentsRepository implements PaymentsQueries, PaymentsUpdates {

    @PersistenceContext
    @Setter
    private EntityManager entityManager;
    @Setter
    @Autowired
    private JpaPersistenceMapper jpaPersistenceMapper;

    @Override
    public Payment save(Payment payment) {
        var entity = jpaPersistenceMapper.toEntity(payment);
        entityManager.persist(entity);
        return jpaPersistenceMapper.toDomain(entity);
    }

    @Override
    public Optional<Payment> findById(String id) {
        var entity = entityManager.find(PaymentEntity.class, id);
        if (entity == null) {
            return Optional.empty();
        }
        return Optional.of(jpaPersistenceMapper.toDomain(entity));
    }

}
