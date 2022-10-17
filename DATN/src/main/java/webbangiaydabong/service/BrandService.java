package webbangiaydabong.service;

import webbangiaydabong.dto.BrandDTO;
import webbangiaydabong.entity.Brand;

import java.util.List;

public interface BrandService {
    Brand findById(Long id);
    BrandDTO saveOrUpdate(BrandDTO dto);
    List<BrandDTO>getAllBrandDtos();
}
