package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webbangiaydabong.entity.Brand;
import webbangiaydabong.repository.BrandRepository;
import webbangiaydabong.service.BrandService;
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository brandRepository;

    @Override
    public Brand findById(Long id) {
        return brandRepository.findById(id).get();
    }
}
