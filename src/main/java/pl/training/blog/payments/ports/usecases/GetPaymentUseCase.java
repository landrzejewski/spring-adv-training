package pl.training.blog.payments.ports.usecases;

import pl.training.blog.payments.domain.Payment;

public interface GetPaymentUseCase {

    Payment findById(String id);

}
