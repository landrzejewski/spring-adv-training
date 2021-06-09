package pl.training.blog.payments.adapters;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import pl.training.blog.Application;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.training.blog.payments.adapters.PaymentsFixture.*;
import static pl.training.blog.payments.adapters.Tags.INTEGRATION;

@Tag(INTEGRATION)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Application.class
)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class GetPaymentIntegrationTests {

    @Autowired
    private MockMvc mockMvc;
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Test
    public void should_return_payment_by_id() throws Exception {
        entityManager.persist(paymentEntity());
        mockMvc.perform(get("/payments/" + PAYMENT_ID)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(PAYMENT_ID)))
                .andExpect(jsonPath("$.value", is(PAYMENT_VALUE.toString())));
    }

}
