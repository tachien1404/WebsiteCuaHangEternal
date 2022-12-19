package webbangiaydabong.service;

import webbangiaydabong.entity.Product;
import webbangiaydabong.entity.PromotionDetails;

import java.util.Date;
import java.util.List;

public interface PromotionDetaitlsService {
    List<PromotionDetails> saveAll(List<PromotionDetails> promotionDetailsList);
    PromotionDetails findByProduct(Long idProduct);
    List<PromotionDetails> findAll();
    List<PromotionDetails> findByPromotionId(Long id);
}
