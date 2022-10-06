package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webbangiaydabong.entity.Promotion;
import webbangiaydabong.repository.PromotionRepository;
import webbangiaydabong.service.PromotionService;

import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    PromotionRepository promotionRepo;

    @Override
    public List<Promotion> findAll() {
        return promotionRepo.findAll();
    }

    @Override
    public Promotion findById(Long id) {
        return promotionRepo.findById(id).get();
    }

    @Override
    public Promotion create(Promotion promotion) {
        return promotionRepo.save(promotion);
    }

    @Override
    public Promotion update(Promotion promotion) {
        return promotionRepo.save(promotion);
    }

    @Override
    public void delete(Long id) {
        promotionRepo.deleteById(id);
    }
}
