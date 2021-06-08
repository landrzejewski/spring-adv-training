package pl.training.blog;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.training.blog.articles.adapters.persistence.jpa.ArticleEntity;
import pl.training.blog.articles.adapters.persistence.jpa.SpringArticlesRepository;
import pl.training.blog.commons.money.LocalMoney;
import pl.training.blog.payments.adapters.persitence.jpa.SpringPaymentsRepository;
import pl.training.blog.payments.application.PaymentRequest;
import pl.training.blog.payments.domain.PaymentStatus;
import pl.training.blog.payments.ports.usecases.ProcessPaymentUseCase;

import java.time.Instant;

import static java.util.Collections.emptyMap;
import static pl.training.blog.payments.domain.PaymentStatus.CANCELED;
import static pl.training.blog.payments.domain.PaymentStatus.STARTED;

@SpringBootApplication
@Log
@RequiredArgsConstructor
public class Application implements ApplicationRunner {

    private final ProcessPaymentUseCase payments;
    private final SpringPaymentsRepository paymentsRepository;
    private final SpringArticlesRepository springArticlesRepository;

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
        // var result = paymentsRepository.findFailedPayments(0, 10);
        // var result = paymentsRepository.findPaymentInfoById(payment.getId());
        // paymentsRepository.findPaymentStatusById(payment.getId())
        //        .ifPresent(description -> System.out.println(description.getFullInfo()));

        springArticlesRepository.saveAndFlush(ArticleEntity.builder()
                .category("Programowanie")
                .rating(5)
                .publicationDate(Instant.now())
                .title("Spring in action")
                .text("Content...")
                .build()
        );
        springArticlesRepository.saveAndFlush(ArticleEntity.builder()
                .category("Testowanie")
                .rating(5)
                .publicationDate(Instant.now())
                .title("TDD in action")
                .text("Content...")
                .build()
        );
        var result = springArticlesRepository.findAll((root, criteriaQuery, builder)
                -> builder.like(builder.lower(root.get("title")), "spring%"));
        System.out.println(result);

    }

}
