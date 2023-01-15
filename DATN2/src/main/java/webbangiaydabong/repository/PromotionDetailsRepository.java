package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import webbangiaydabong.entity.Product;
import webbangiaydabong.entity.Promotion;
import webbangiaydabong.entity.PromotionDetails;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public interface PromotionDetailsRepository extends JpaRepository<PromotionDetails,Long> {
    @Query("select p from PromotionDetails  p where  p.product.id = ?1")
    PromotionDetails findByProduct(Long idProduct);

    @Query("select p from PromotionDetails p where p.promotion.id = ?1")
    List<PromotionDetails> findPromotion(Long id);
}
