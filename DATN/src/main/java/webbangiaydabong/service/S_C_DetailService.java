package webbangiaydabong.service;

import webbangiaydabong.entity.S_C_Details;

import java.util.List;

public interface S_C_DetailService {
    List<S_C_Details> findAll();
    S_C_Details findBySizeColor(Long productId ,Long sizeId, Long colorId);

    List<S_C_Details> findBySize(Long productId, Long sizeId);

    List<S_C_Details> findByColor(Long productId, Long colorId);

    S_C_Details create(S_C_Details sizeColorDetails);
    S_C_Details update(S_C_Details sizeColorDetails);

    void delete(Long id);
}
