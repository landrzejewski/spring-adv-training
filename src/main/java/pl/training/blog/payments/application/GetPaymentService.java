package pl.training.blog.payments.application;

import lombok.RequiredArgsConstructor;
import pl.training.blog.payments.domain.Payment;
import pl.training.blog.payments.ports.persistence.PaymentsQueries;
import pl.training.blog.payments.ports.usecases.GetPaymentUseCase;

import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
public class GetPaymentService implements GetPaymentUseCase {

    private final PaymentsQueries paymentsQueries;

    @Override
    public Payment findById(String id) {
        return paymentsQueries.findById(id)
                .orElseThrow(PaymentNotFoundException::new);
    }

}
