package pl.training.blog.payments;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.training.blog.payments.PaymentsFixtures.*;

@ExtendWith(MockitoExtension.class)
class MockistPaymentsServiceTest {

    @Mock
    private PaymentsRepository paymentsRepository;
    private PaymentsService sut;

    @BeforeEach
    void beforeEach() {
        //paymentRepository = mock(PaymentRepository.class);
        when(paymentsRepository.save(any(Payment.class))).then(returnsFirstArg());
        sut = new PaymentsService(() -> PAYMENT_ID, paymentsRepository, () -> TIMESTAMP);
    }

    @Test
    void given_a_payment_request_when_process_then_returns_a_valid_payment() {
        assertEquals(EXPECTED_PAYMENT, sut.process(VALID_PAYMENT_REQUEST));
    }

    @Disabled("This is unnecessary coupling, its better to do an integration test")
    @Test
    void given_a_payment_request_when_process_the_created_payment_is_persisted() {
        var payment = sut.process(VALID_PAYMENT_REQUEST);
        verify(paymentsRepository).save(payment);
    }

}
