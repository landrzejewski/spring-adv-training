package pl.training.blog.payments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.blog.payments.adapters.logging.FilePaymentsLogger;
import pl.training.blog.payments.application.GetPaymentService;
import pl.training.blog.payments.application.PaymentIdGenerator;
import pl.training.blog.payments.application.ProcessPaymentService;
import pl.training.blog.payments.application.UUIDPaymentIdGenerator;
import pl.training.blog.payments.ports.persistence.PaymentsQueries;
import pl.training.blog.payments.ports.persistence.PaymentsUpdates;
import pl.training.blog.payments.ports.time.TimeProvider;
import pl.training.blog.payments.ports.usecases.GetPaymentUseCase;
import pl.training.blog.payments.ports.usecases.ProcessPaymentUseCase;

import java.nio.file.Paths;

@Configuration
public class PaymentsConfiguration {

    @Bean
    public FilePaymentsLogger filePaymentLogger() {
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
