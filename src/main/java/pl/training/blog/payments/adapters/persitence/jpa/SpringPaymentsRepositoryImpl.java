package pl.training.blog.payments.adapters.persitence.jpa;

import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static pl.training.blog.payments.adapters.persitence.jpa.PaymentEntity.FIND_FAILED_PAYMENTS;

public class SpringPaymentsRepositoryImpl implements SpringPaymentsRepositoryCustom {

    @PersistenceContext
    @Setter
    private EntityManager entityManager;

    @Override
    public List<PaymentEntity> findFailedPayments(int pageNumber, int pageSize) {
        return entityManager.createNamedQuery(FIND_FAILED_PAYMENTS, PaymentEntity.class)
                .setFirstResult(pageNumber * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

}
