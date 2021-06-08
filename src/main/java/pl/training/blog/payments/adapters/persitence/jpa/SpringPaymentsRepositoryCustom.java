package pl.training.blog.payments.adapters.persitence.jpa;

import java.util.List;

public interface SpringPaymentsRepositoryCustom {

    List<PaymentEntity> findFailedPayments(int pageNumber, int pageSize);

}
