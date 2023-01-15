package webbangiaydabong.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import webbangiaydabong.entity.Promotion;

import java.util.Date;
import java.util.List;

public interface PromotionService {
    List<Promotion> findAll();
    Promotion findById(Long id);
    Promotion create(Promotion promotion);
    Promotion update(Promotion promotion);
    void delete(Long id);

    Page<Promotion> findByKey(
            Pageable pageable,
            String name,
            Long id,
            Date starttime,
            Date endtime,
            Integer value
    );

}
