package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import webbangiaydabong.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
}
