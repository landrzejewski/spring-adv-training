package pl.training.shop.payments.adapters.providers;

import org.springframework.stereotype.Service;
import pl.training.shop.payments.ports.providers.TimeProvider;

import java.time.Instant;

@Service
public class SystemTimeProvider implements TimeProvider {

    @Override
    public Instant getTimestamp() {
        return Instant.now();
    }

}
