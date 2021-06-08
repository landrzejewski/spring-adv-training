package pl.training.blog.payments.adapters.persitence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringPaymentsRepository extends JpaRepository<PaymentEntity, String>, SpringPaymentsRepositoryCustom {

    List<PaymentEntity> findByStatusOrderByTimestampAsc(String status);

    @Query("select p from PaymentEntity p where p.status = :status order by p.timestamp asc")
    List<PaymentEntity> findByStatus(@Param("status") String status);

    List<PaymentEntity> findByStatusValue(String status);

}
