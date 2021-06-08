package pl.training.blog.payments.adapters.persitence.jpa;

import org.springframework.beans.factory.annotation.Value;

public interface PaymentDescription {

    String getId();
    String getStatus();
    @Value("#{target.id + ':' + target.status}")
    String getFullInfo();

}
