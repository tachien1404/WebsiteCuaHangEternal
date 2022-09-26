package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import webbangiaydabong.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
}
