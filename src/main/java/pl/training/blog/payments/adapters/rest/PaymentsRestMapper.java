package pl.training.blog.payments.adapters.rest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import pl.training.blog.commons.money.FastMoneyMapper;
import pl.training.blog.payments.application.PaymentRequest;
import pl.training.blog.payments.domain.Payment;
import pl.training.blog.payments.domain.PaymentStatus;

@Mapper(componentModel = "spring", uses = FastMoneyMapper.class)
interface PaymentsRestMapper {

    @Mapping(source = "properties", target = "additionalInfo")
    PaymentRequest toDomain(PaymentRequestDto paymentRequestDto);

    PaymentDto toDto(Payment payment);

    @ValueMapping(source = "STARTED", target = "NOT_CONFIRMED")
    @ValueMapping(source = "FAILED", target = "NOT_CONFIRMED")
    @ValueMapping(source = "CANCELED", target = "NOT_CONFIRMED")
    PaymentStatusDto toDto(PaymentStatus status);

}