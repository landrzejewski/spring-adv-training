package pl.training.blog.payments.adapters.persitence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SpringPaymentsRepository extends JpaRepository<PaymentEntity, String>, SpringPaymentsRepositoryCustom {

    List<PaymentEntity> findByStatusOrderByTimestampAsc(String status);

    @Query("select p from PaymentEntity p where p.status = :status order by p.timestamp asc")
    List<PaymentEntity> findByStatus(@Param("status") String status);

    List<PaymentEntity> findByStatusValue(String status);

    @Query("select new pl.training.blog.payments.adapters.persitence.jpa.PaymentInfo(p.id, p.status) from PaymentEntity p where p.id = :id")
    Optional<PaymentInfo> findPaymentInfoById(@Param("id") String id);

    @Query("select p.id as id, p.status as status from PaymentEntity p where p.id = :id")
    Optional<PaymentDescription> findPaymentStatusById(@Param("id") String id);

}