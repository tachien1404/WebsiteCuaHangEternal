package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import webbangiaydabong.entity.Exchange;
@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long>{

}
