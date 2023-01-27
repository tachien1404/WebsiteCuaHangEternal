package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webbangiaydabong.entity.Product;
import webbangiaydabong.entity.PromotionDetails;
import webbangiaydabong.repository.PromotionDetailsRepository;
import webbangiaydabong.service.PromotionDetaitlsService;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Service
public class PromotionDetailsServiceImpl implements PromotionDetaitlsService {
    @Autowired
    PromotionDetailsRepository promotionDetailsRepository;
    @Override
    public List<PromotionDetails> saveAll(List<PromotionDetails> promotionDetailsList) {
        return promotionDetailsRepository.saveAll(promotionDetailsList);
    }

    @Override
    public PromotionDetails findByProduct(Long idProduct) {
        return promotionDetailsRepository.findByProduct(idProduct);
    }

    @Override
    public List<PromotionDetails> findAll() {
        return promotionDetailsRepository.findAll();
    }

    @Override
    public List<PromotionDetails> findByPromotionId(Long id) {
        return promotionDetailsRepository.findPromotion(id);
    }

    @Override
    public PromotionDetails save(PromotionDetails promotionDetails) {
        return promotionDetailsRepository.save(promotionDetails);
    }
}
