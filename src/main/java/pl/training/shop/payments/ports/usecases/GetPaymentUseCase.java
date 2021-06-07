package pl.training.shop.payments.ports.usecases;

import pl.training.shop.payments.domain.Payment;

public interface GetPaymentUseCase {

    Payment findById(String id);

}
