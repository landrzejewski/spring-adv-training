package pl.training.blog.payments.adapters.time;

import org.springframework.stereotype.Service;
import pl.training.blog.payments.ports.time.TimeProvider;

import java.time.Instant;

@Service
public class SystemTimeProvider implements TimeProvider {

    @Override
    public Instant getTimestamp() {
        return Instant.now();
    }

}
