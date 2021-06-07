package pl.training.shop.payments.adapters.persitence.jpa;

import org.mapstruct.Mapper;
import pl.training.shop.payments.domain.Payment;

@Mapper(componentModel = "spring")
public interface JpaModelMapper {

    PaymentEntity toEntity(Payment payment);

    Payment toDomain(PaymentEntity paymentEntity);

}
