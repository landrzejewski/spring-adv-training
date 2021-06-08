package pl.training.blog.commons.time;

import java.time.Instant;

public interface TimeProvider {

    Instant getTimestamp();

}
