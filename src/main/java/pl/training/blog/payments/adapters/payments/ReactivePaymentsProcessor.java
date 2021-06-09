package pl.training.blog.payments.adapters.payments;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.training.blog.payments.ports.payments.PaymentsProcessor;
import pl.training.blog.payments.domain.Payment;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@ConfigurationProperties("payments-processor")
@Service
@Setter
@RequiredArgsConstructor
public class ReactivePaymentsProcessor implements PaymentsProcessor {

    private String baseUrl;
    private String path;

    private final PaymentsModelMapper modelMapper;

    @PostConstruct
    public void init() {
        WebClient.builder().baseUrl(baseUrl).build()
                .get()
                .uri(path)
                .retrieve()
                .bodyToFlux(PaymentDto.class)
                .subscribe(System.out::println, System.out::println, () -> System.out.println("Completed"));
    }

    @Override
    public Mono<Payment> process(Payment payment) {
        return WebClient.builder().baseUrl(baseUrl).build()
                .post()
                .uri(path)
                .bodyValue(modelMapper.toDto(payment))
                .retrieve()
                .bodyToMono(PaymentDto.class)
                .map(modelMapper::toDomain);
    }

}
