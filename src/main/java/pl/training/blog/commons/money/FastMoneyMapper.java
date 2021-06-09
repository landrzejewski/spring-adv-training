package pl.training.blog.commons.money;

import org.javamoney.moneta.FastMoney;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FastMoneyMapper {

    default FastMoney toFastMoney(String text) {
        return text != null ? FastMoney.parse(text) : LocalMoney.zero();
    }

    default String toText(FastMoney fastMoney) {
        return fastMoney != null ? fastMoney.toString() : "";
    }

}