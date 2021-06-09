package pl.training.blog.payments.adapters.persitence.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.training.blog.commons.money.LocalMoney;
import pl.training.blog.payments.domain.PaymentStatus;

import java.time.Instant;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.training.blog.payments.adapters.persitence.jpa.PaymentsFixture.STARTED_STATUS;
import static pl.training.blog.payments.adapters.persitence.jpa.PaymentsFixture.paymentEntity;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class SpringPaymentsRepositoryTest {

    private final PaymentEntity paymentEntity = paymentEntity();

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
        var actual = paymentsRepository.findByStatus(STARTED_STATUS);
        assertTrue(actual.contains(paymentEntity));
    }

}