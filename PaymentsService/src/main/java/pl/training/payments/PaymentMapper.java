package pl.training.payments;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = FastMoneyMapper.class)
public interface PaymentMapper {

    Payment toDomain(PaymentDto paymentDto);

    PaymentDto toDto(Payment payment);

    PaymentDocument toDocument(Payment payment);

    Payment toDomain(PaymentDocument paymentDocument);

}
