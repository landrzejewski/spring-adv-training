package pl.training.blog.payments.adapters;

import pl.training.blog.commons.money.LocalMoney;
import pl.training.blog.payments.adapters.persitence.jpa.PaymentEntity;
import pl.training.blog.payments.domain.Payment;
import pl.training.blog.payments.domain.PaymentStatus;

import java.time.Instant;
import java.util.Collections;
import java.util.UUID;

public class PaymentsFixture {

    public static final Payment PAYMENT = Payment.builder()
            .id(UUID.randomUUID().toString())
            .properties(Collections.emptyMap())
            .status(PaymentStatus.STARTED)
            .timestamp(Instant.now())
            .value(LocalMoney.of(1_000))
            .build();
    public static final String STARTED_STATUS = PaymentStatus.STARTED.name();
    public static PaymentEntity paymentEntity() {
        var entity = new PaymentEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setProperties(Collections.emptyMap());
        entity.setStatus(STARTED_STATUS);
        entity.setTimestamp(Instant.now());
        entity.setValue(LocalMoney.of(1_000));
        return entity;
    }

}
