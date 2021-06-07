package pl.training.shop.payments.adapters.provider;

import org.springframework.stereotype.Service;
import pl.training.shop.payments.ports.provider.TimeProvider;

import java.time.Instant;

@Service
public class SystemTimeProvider implements TimeProvider {

    @Override
    public Instant getTimestamp() {
        return Instant.now();
    }

}
