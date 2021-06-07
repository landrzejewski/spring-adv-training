package pl.training.shop.payments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.nio.file.Paths;

@EnableAspectJAutoProxy
@Configuration
public class PaymentsConfiguration {

    @Bean
    public PaymentFileLogger paymentFileLogger() {
        return new PaymentFileLogger(Paths.get("logs.txt"));
    }

}
