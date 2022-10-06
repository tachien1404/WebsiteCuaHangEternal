package webbangiaydabong.service;

import webbangiaydabong.entity.Promotion;
import java.util.List;

public interface PromotionService {
    List<Promotion> findAll();
    Promotion findById(Long id);
    Promotion create(Promotion promotion);
    Promotion update(Promotion promotion);
    void delete(Long id);
}
