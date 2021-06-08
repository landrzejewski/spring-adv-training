package pl.training.blog;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.training.blog.commons.money.LocalMoney;
import pl.training.blog.payments.adapters.persitence.jpa.SpringPaymentsRepository;
import pl.training.blog.payments.application.PaymentRequest;
import pl.training.blog.payments.domain.PaymentStatus;
import pl.training.blog.payments.ports.usecases.ProcessPaymentUseCase;

import static java.util.Collections.emptyMap;
import static pl.training.blog.payments.domain.PaymentStatus.CANCELED;
import static pl.training.blog.payments.domain.PaymentStatus.STARTED;

@SpringBootApplication
@Log
@RequiredArgsConstructor
public class Application implements ApplicationRunner {

    private final ProcessPaymentUseCase payments;
    private final SpringPaymentsRepository paymentsRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var paymentRequest = new PaymentRequest(LocalMoney.of(1_000), emptyMap());
        var payment = payments.process(paymentRequest);
        // var result = paymentsRepository.findByStatus(STARTED.name());
        // var result = paymentsRepository.findByStatusOrderByTimestampAsc(STARTED.name());
        // var result = paymentsRepository.findByStatusValue(STARTED.name());
        var result = paymentsRepository.findFailedPayments(0, 10);
        System.out.println(result);
    }

}
