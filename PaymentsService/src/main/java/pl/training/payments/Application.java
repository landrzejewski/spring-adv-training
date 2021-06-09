package pl.training.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements ApplicationRunner {

    private final PaymentsRepository paymentsRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var payment = new PaymentDocument();
        payment.setStatus("STARTED");
        payment.setTimestamp(Instant.now());
        payment.setRequestId("123");
        payment.setValue("100 PLN");
        paymentsRepository.save(payment)
                .subscribe(System.out::println);
    }

}
