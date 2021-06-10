package pl.training.blog.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.training.blog.payments.domain.Payment;
import pl.training.blog.payments.ports.usecases.GetPaymentUseCase;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.training.blog.payments.adapters.PaymentsFixture.*;

//@WebMvcTest(GetPaymentController.class)
//@ExtendWith(SpringExtension.class)
public class GetPaymentControllerTests {

    //@Autowired
    private MockMvc mockMvc;
    //@MockBean
    private GetPaymentUseCase getPaymentUseCase;
    //@MockBean
    private PaymentsRestMapper paymentsRestMapper;
    private final Payment payment = PAYMENT;

    //@BeforeEach
    public void setup() {
        when(getPaymentUseCase.findById(PAYMENT_ID)).thenReturn(payment);
        when(paymentsRestMapper.toDto(any(Payment.class))).thenReturn(paymentDto());
    }

    //@Test
    public void should_return_payment_by_id() throws Exception {
        mockMvc.perform(get("/payments/" + PAYMENT_ID)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(PAYMENT_ID)))
                .andExpect(jsonPath("$.value", is(PAYMENT_VALUE.toString())));
    }

}
