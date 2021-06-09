package pl.training.blog.payments.adapters.persitence.jpa;

import pl.training.blog.commons.money.LocalMoney;
import pl.training.blog.payments.domain.PaymentStatus;

import java.time.Instant;
import java.util.Collections;
import java.util.UUID;

public class PaymentsFixture {

    static final String STARTED_STATUS = PaymentStatus.STARTED.name();
    static PaymentEntity paymentEntity() {
        var entity = new PaymentEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setProperties(Collections.emptyMap());
        entity.setStatus(STARTED_STATUS);
        entity.setTimestamp(Instant.now());
        entity.setValue(LocalMoney.of(1_000));
        return entity;
    }

}
