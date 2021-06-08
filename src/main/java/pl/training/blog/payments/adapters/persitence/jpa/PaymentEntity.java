package pl.training.blog.payments.adapters.persitence.jpa;

import lombok.Data;
import org.javamoney.moneta.FastMoney;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;

@Table(name = "payments")
@Entity
@Data
public class PaymentEntity {

    @Id
    @Pattern(regexp = "\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}")
    private String id;
    @NotNull
    private FastMoney value;
    @ElementCollection
    @CollectionTable(name = "PAYMENTS_PROPERTIES", joinColumns=@JoinColumn(name = "payment_id"))
    @MapKeyColumn(name = "KEY")
    @Column(name = "VALUE")
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
