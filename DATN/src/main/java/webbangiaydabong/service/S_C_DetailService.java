package webbangiaydabong.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import webbangiaydabong.entity.*;

import java.util.List;

public interface S_C_DetailService {
    List<S_C_Details> findAll();
    S_C_Details findBySizeColor(Long productId ,Long sizeId, Long colorId);

    List<S_C_Details> findBySize(Long productId, Long sizeId);

    List<S_C_Details> findByColor(Long productId, Long colorId);

    S_C_Details create(S_C_Details sizeColorDetails);
    S_C_Details update(S_C_Details sizeColorDetails);

    void delete(Long id);

    Page<S_C_Details> findByKey(Pageable pageable,
                            Long id,
                            Product product,
                            size size,
                            Color mau,
                            Integer quantity,
                            Integer status);

}
