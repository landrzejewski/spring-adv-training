package pl.training.blog.payments;

public interface Payments {

    Payment process(PaymentRequest paymentRequest);

    Payment findById(String id);

}
