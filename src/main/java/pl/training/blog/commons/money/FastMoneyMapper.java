package pl.training.blog.commons.money;

import org.javamoney.moneta.FastMoney;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FastMoneyMapper {

    default String toString(FastMoney fastMoney) {
        return fastMoney != null ? fastMoney.toString() : "";
    }

    default FastMoney toFastMoney(String value) {
        return value != null ? FastMoney.parse(value) : null;
    }

}
