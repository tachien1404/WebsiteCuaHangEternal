package webbangiaydabong.service;

import webbangiaydabong.dto.BrandDTO;
import webbangiaydabong.entity.Brand;

import java.util.List;

public interface BrandService {
    Brand findById(Long id);
    BrandDTO saveOrUpdate(BrandDTO dto);
    List<BrandDTO>getAllBrandDtos();
    List<Brand>  getAll();
    boolean checkName(String name);
    Brand getByName(String name);
    Brand create(Brand brand);
}
