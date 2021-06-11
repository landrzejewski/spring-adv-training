package pl.training.payments.adapters.persitence.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.training.payments.adapters.PaymentsFixture;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class SpringPaymentsRepositoryTests {

    private final PaymentEntity paymentEntity = PaymentsFixture.paymentEntity();

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private SpringPaymentsRepository paymentsRepository;

    @BeforeEach
    public void setup() {
        entityManager.persist(paymentEntity);
        entityManager.flush();
    }

    @Test
    public void given_payment_with_particular_status_in_database_when_find_by_status_then_returns_the_payment() {
        var actual = paymentsRepository.findByStatus(PaymentsFixture.PAYMENT_STATUS.name());
        assertTrue(actual.contains(paymentEntity));
    }

}