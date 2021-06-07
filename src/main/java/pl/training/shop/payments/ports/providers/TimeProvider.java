package pl.training.shop.payments.ports.providers;

import java.time.Instant;

public interface TimeProvider {

    Instant getTimestamp();

}
