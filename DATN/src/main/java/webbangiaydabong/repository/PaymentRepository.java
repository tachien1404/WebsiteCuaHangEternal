package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import webbangiaydabong.entity.Payment;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{
}
