package pl.training.shop.payments;

import lombok.Builder;
import lombok.Value;
import org.javamoney.moneta.FastMoney;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.Instant;
import java.util.Map;

@Builder
@Value
public class Payment {

    @Pattern(regexp = "\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}")
    String id;
    @NotNull
    FastMoney value;
    Map<String, String> properties;
    Instant timestamp;
    PaymentStatus status;

    public boolean hasId(String id) {
        return this.id.equals(id);
    }

}
