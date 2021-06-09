package pl.training.blog.payments.adapters;

import org.javamoney.moneta.FastMoney;
import pl.training.blog.commons.money.LocalMoney;
import pl.training.blog.payments.adapters.persitence.jpa.PaymentEntity;
import pl.training.blog.payments.adapters.rest.PaymentDto;
import pl.training.blog.payments.adapters.rest.PaymentStatusDto;
import pl.training.blog.payments.application.PaymentRequest;
import pl.training.blog.payments.domain.Payment;
import pl.training.blog.payments.domain.PaymentStatus;

import java.time.Instant;
import java.util.UUID;

import static java.util.Collections.emptyMap;

public class PaymentsFixture {

    public static final Instant TIMESTAMP = Instant.now();
    public static final String PAYMENT_ID = UUID.randomUUID().toString();
    public static final FastMoney PAYMENT_VALUE = LocalMoney.of(1_000);
    public static final PaymentStatus PAYMENT_STATUS = PaymentStatus.STARTED;
    public static final Payment PAYMENT = Payment.builder()
            .id(PAYMENT_ID)
            .properties(emptyMap())
            .status(PAYMENT_STATUS)
            .timestamp(TIMESTAMP)
            .value(PAYMENT_VALUE)
            .build();
    public static PaymentEntity paymentEntity() {
        var entity = new PaymentEntity();
        entity.setId(PAYMENT_ID);
        entity.setProperties(emptyMap());
        entity.setStatus(PAYMENT_STATUS.name());
        entity.setTimestamp(TIMESTAMP);
        entity.setValue(PAYMENT_VALUE);
        return entity;
    }
    public static final PaymentRequest PAYMENT_REQUEST = new PaymentRequest(PAYMENT_VALUE, emptyMap());
    public static final PaymentDto paymentDto() {
        var dto = new PaymentDto();
        dto.setId(PAYMENT_ID);
        dto.setTimestamp(TIMESTAMP);
        dto.setValue(PAYMENT_VALUE.toString());
        dto.setStatus(PaymentStatusDto.NOT_CONFIRMED);
        return dto;
    }
}
