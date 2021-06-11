package pl.training.payments.adapters.rest;

import org.hamcrest.core.Is;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.training.blog.payments.domain.Payment;
import pl.training.blog.payments.ports.usecases.GetPaymentUseCase;
import pl.training.payments.adapters.PaymentsFixture;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(GetPaymentController.class)
//@ExtendWith(SpringExtension.class)
public class GetPaymentControllerTests {

    //@Autowired
    private MockMvc mockMvc;
    //@MockBean
    private GetPaymentUseCase getPaymentUseCase;
    //@MockBean
    private PaymentsRestMapper paymentsRestMapper;
    private final Payment payment = PaymentsFixture.PAYMENT;

    //@BeforeEach
    public void setup() {
        when(getPaymentUseCase.findById(PaymentsFixture.PAYMENT_ID)).thenReturn(payment);
        when(paymentsRestMapper.toDto(any(Payment.class))).thenReturn(PaymentsFixture.paymentDto());
    }

    //@Test
    public void should_return_payment_by_id() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/payments/" + PaymentsFixture.PAYMENT_ID)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Is.is(PaymentsFixture.PAYMENT_ID)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value", Is.is(PaymentsFixture.PAYMENT_VALUE.toString())));
    }

}
