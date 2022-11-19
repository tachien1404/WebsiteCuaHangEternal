package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webbangiaydabong.entity.SaleCode;
@Repository
public interface SaleCodeRepository extends JpaRepository<SaleCode,Long> {
}
