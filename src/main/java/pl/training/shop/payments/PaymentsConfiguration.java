package pl.training.shop.payments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;

@Configuration
public class PaymentsConfiguration {

    @Bean
    public FilePaymentsLogger paymentFileLogger() {
        return new FilePaymentsLogger(Paths.get("logs.txt"));
    }

}
