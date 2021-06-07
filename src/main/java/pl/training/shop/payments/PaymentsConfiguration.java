package pl.training.shop.payments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.shop.payments.adapters.logging.FilePaymentsLogger;
import pl.training.shop.payments.application.GetPaymentService;
import pl.training.shop.payments.application.PaymentIdGenerator;
import pl.training.shop.payments.application.ProcessPaymentService;
import pl.training.shop.payments.application.UUIDPaymentIdGenerator;
import pl.training.shop.payments.ports.persistence.PaymentsQueries;
import pl.training.shop.payments.ports.persistence.PaymentsUpdates;
import pl.training.shop.payments.ports.providers.TimeProvider;
import pl.training.shop.payments.ports.usecases.GetPaymentUseCase;
import pl.training.shop.payments.ports.usecases.ProcessPaymentUseCase;

import java.nio.file.Paths;

@Configuration
public class PaymentsConfiguration {

    @Bean
    public FilePaymentsLogger paymentFileLogger() {
        return new FilePaymentsLogger(Paths.get("logs.txt"));
    }

    @Bean
    public PaymentIdGenerator paymentIdGenerator() {
        return new UUIDPaymentIdGenerator();
    }

    @Bean
    public ProcessPaymentUseCase processPaymentUseCase(PaymentIdGenerator paymentIdGenerator, PaymentsUpdates paymentsUpdates, TimeProvider timeProvider) {
        return new ProcessPaymentService(paymentIdGenerator, paymentsUpdates, timeProvider);
    }

    @Bean
    public GetPaymentUseCase getPaymentUseCase(PaymentsQueries paymentsQueries) {
        return new GetPaymentService(paymentsQueries);
    }

}
