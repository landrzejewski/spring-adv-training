package pl.training.blog.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.training.blog.payments.ports.usecases.ProcessPaymentUseCase;

import static pl.training.blog.commons.web.UriBuilder.requestUriWithId;

@RestController
@RequiredArgsConstructor
public class ProcessPaymentController {

    private final PaymentsRestMapper modelMapper;
    private final ProcessPaymentUseCase processPaymentUseCase;

    @PostMapping("payments")
    public ResponseEntity<PaymentDto> process(@RequestBody PaymentRequestDto paymentRequestDto) {
        var paymentRequest = modelMapper.toDomain(paymentRequestDto);
        var payment = processPaymentUseCase.process(paymentRequest);
        var paymentDto = modelMapper.toDto(payment);
        var locationUri = requestUriWithId(paymentDto.getId());
        return ResponseEntity.created(locationUri)
                .body(paymentDto);
    }

}
