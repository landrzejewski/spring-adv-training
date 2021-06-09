package pl.training.blog.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.training.blog.payments.adapters.PaymentsFixture;
import pl.training.blog.payments.domain.Payment;
import pl.training.blog.payments.ports.usecases.GetPaymentUseCase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pl.training.blog.payments.adapters.PaymentsFixture.PAYMENT;

@WebMvcTest(GetPaymentController.class)
@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor
public class GetPaymentControllerTests {

    private final MockMvc mockMvc;
    @MockBean
    private final GetPaymentUseCase getPaymentUseCase;
    @MockBean
    private final PaymentsRestMapper paymentsRestMapper;
    private final Payment payment = PAYMENT;

    @BeforeEach
    public void setup() {
        when(getPaymentUseCase.findById(payment.getId())).thenReturn(payment);
        when(paymentsRestMapper.toDomain(any(PaymentRequestDto.class))).thenReturn(payment);

    }

}
