package pl.training.blog.payments.ports.time;

import java.time.Instant;

public interface TimeProvider {

    Instant getTimestamp();

}
