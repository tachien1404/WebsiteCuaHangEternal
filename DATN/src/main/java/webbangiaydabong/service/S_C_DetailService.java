package webbangiaydabong.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import webbangiaydabong.dto.S_C_DetailDTO;
import webbangiaydabong.dto.SizeDTO;
import webbangiaydabong.entity.*;

import java.util.List;

public interface S_C_DetailService {
    List<S_C_Details> findAll();
List<S_C_DetailDTO>congsl(List<S_C_DetailDTO>dto);

    List<S_C_DetailDTO>trusl(List<S_C_DetailDTO>dto);
    S_C_Details findBySizeColor(Long productId, Long sizeId, Long colorId);
    S_C_Details findBySizeColor1(Long productId, Long sizeId, Long colorId);
    Page<S_C_Details> findConfigProduct(Pageable pageable,Long productId);

    S_C_Details create(S_C_Details sizeColorDetails);

    S_C_Details update(S_C_Details sizeColorDetails);

    void delete(Long id);

    Page<S_C_Details> findByKey(Pageable pageable,
                                Long id,
                                Product product,
                                Size size,
                                Color mau,
                                Integer quantity,
                                Integer status);

    List<S_C_Details> findQuantity(Product product,
                                   Size size,
                                   Color mau);
    List<SizeDTO>getsize(S_C_DetailDTO dto);

    List<Size> sizeAvailable(Long idProduct);
    List<Color> colorAvailable(Long idProduct);

    S_C_Details findById(Long id);
}
