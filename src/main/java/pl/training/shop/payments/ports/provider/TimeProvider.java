package pl.training.shop.payments.ports.provider;

import java.time.Instant;

public interface TimeProvider {

    Instant getTimestamp();

}
