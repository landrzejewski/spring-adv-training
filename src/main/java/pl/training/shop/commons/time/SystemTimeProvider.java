package pl.training.shop.commons.time;

import java.time.Instant;

public class SystemTimeProvider implements TimeProvider {

    @Override
    public Instant getTimestamp() {
        return Instant.now();
    }

}
