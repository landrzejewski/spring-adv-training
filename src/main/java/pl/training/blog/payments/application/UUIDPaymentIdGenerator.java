package pl.training.blog.payments.application;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UUIDPaymentIdGenerator implements PaymentIdGenerator {

    public String getNext() {
        return UUID.randomUUID().toString();
    }

}
