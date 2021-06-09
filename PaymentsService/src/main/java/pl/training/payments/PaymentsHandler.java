package pl.training.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PaymentsHandler {

    private final PaymentMapper paymentMapper;
    private final PaymentsService paymentsService;

    public Mono<ServerResponse> getPayments(ServerRequest serverRequest) {
        var payments = paymentsService.getPayments().map(paymentMapper::toDto);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_NDJSON)
                //.contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(payments, PaymentDto.class);
    }

    public Mono<ServerResponse> process(ServerRequest serverRequest) {
        var result = paymentsService.process(serverRequest.bodyToMono(PaymentDto.class).map(paymentMapper::toDomain));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(result, PaymentDto.class);
    }

}
