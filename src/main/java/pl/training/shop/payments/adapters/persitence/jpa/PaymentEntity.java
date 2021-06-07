package pl.training.shop.payments.adapters.persitence.jpa;

import lombok.Data;
import org.javamoney.moneta.FastMoney;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;

@Entity
@Data
public class PaymentEntity {

    @Id
    @Pattern(regexp = "\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}")
    private String id;
    @NotNull
    private FastMoney value;
    @ElementCollection
    private Map<String, String> properties;
    private Instant timestamp;
    private String status;

    @Override
    public boolean equals(Object otherPayment) {
        if (this == otherPayment) {
            return true;
        }
        if (!(otherPayment instanceof PaymentEntity)) {
            return false;
        }
        var payment = (PaymentEntity) otherPayment;
        return Objects.equals(id, payment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
