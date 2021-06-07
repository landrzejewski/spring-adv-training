package pl.training.shop.payments.adapters.persitence.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.training.shop.payments.domain.Payment;
import pl.training.shop.payments.ports.persistence.PaymentsQueries;
import pl.training.shop.payments.ports.persistence.PaymentsUpdates;

import java.util.Optional;

@Primary
@Repository
@RequiredArgsConstructor
public class SpringDataJpaPaymentsRepository implements PaymentsQueries, PaymentsUpdates {

    private final SpringPaymentsRepository paymentsRepository;
    private final JpaModelMapper modelMapper;

    @Override
    public Payment save(Payment payment) {
        var entity = modelMapper.toEntity(payment);
        var result = paymentsRepository.save(entity);
        return modelMapper.toDomain(result);
    }

    @Override
    public Optional<Payment> findById(String id) {
        return paymentsRepository.findById(id).map(modelMapper::toDomain);
    }

}
