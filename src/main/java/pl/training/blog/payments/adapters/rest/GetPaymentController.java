package pl.training.blog.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.training.blog.payments.ports.usecases.GetPaymentUseCase;

@RestController
@RequiredArgsConstructor
public class GetPaymentController {

    private final PaymentsRestMapper modelMapper;
    private final GetPaymentUseCase getPaymentUseCase;

    @GetMapping("payments/{id}")
    ResponseEntity<PaymentDto> findById(@PathVariable("id") String id) {
        var payment = getPaymentUseCase.findById(id);
        var paymentDto = modelMapper.toDto(payment);
        return ResponseEntity.ok(paymentDto);
    }

}
