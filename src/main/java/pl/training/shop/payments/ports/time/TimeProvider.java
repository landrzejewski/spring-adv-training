package pl.training.shop.payments.ports.time;

import java.time.Instant;

public interface TimeProvider {

    Instant getTimestamp();

}
