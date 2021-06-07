package pl.training.shop.payments.application;

import lombok.RequiredArgsConstructor;
import pl.training.shop.payments.domain.Payment;
import pl.training.shop.payments.ports.persistence.PaymentsQueries;
import pl.training.shop.payments.ports.usecases.GetPaymentUseCase;

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
