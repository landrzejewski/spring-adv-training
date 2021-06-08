package pl.training.blog.payments.adapters.persitence.jpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface SpringPaymentsRepository extends JpaRepository<PaymentEntity, String>, SpringPaymentsRepositoryCustom {

    //@Lock(LockModeType.READ)
    @EntityGraph(value = "PaymentEntity.eagerProperties", type = EntityGraph.EntityGraphType.LOAD)
    List<PaymentEntity> findByStatusOrderByTimestampAsc(String status);

    @Query("select p from PaymentEntity p where p.status = :status order by p.timestamp asc")
    @EntityGraph(attributePaths = { "properties" })
    List<PaymentEntity> findByStatus(@Param("status") String status);

    @EntityGraph(attributePaths = { "properties" })
    List<PaymentEntity> findByStatusValue(String status);

    @Query("select new pl.training.blog.payments.adapters.persitence.jpa.PaymentInfo(p.id, p.status) from PaymentEntity p where p.id = :id")
    @EntityGraph(attributePaths = { "properties" })
    Optional<PaymentInfo> findPaymentInfoById(@Param("id") String id);

    @Query("select p.id as id, p.status as status from PaymentEntity p where p.id = :id")
    @EntityGraph(attributePaths = { "properties" })
    Optional<PaymentDescription> findPaymentStatusById(@Param("id") String id);

}