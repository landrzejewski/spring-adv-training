package pl.training.blog.payments.adapters.payments;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.training.blog.commons.money.FastMoneyMapper;
import pl.training.blog.payments.domain.Payment;

@Mapper(componentModel = "spring", uses = FastMoneyMapper.class)
public interface PaymentsModelMapper {

    @Mapping(source = "id", target = "requestId")
    PaymentDto toDto(Payment payment);

    @InheritInverseConfiguration
    Payment toDomain(PaymentDto paymentDto);

}
