package pl.training.payments;

import org.javamoney.moneta.FastMoney;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", imports = FastMoneyMapper.class)
public interface PaymentMapper {

    Payment toDomain(PaymentDto paymentDto);

    PaymentDto toDto(Payment payment);

    FastMoney toFastMoney(String value);

}
