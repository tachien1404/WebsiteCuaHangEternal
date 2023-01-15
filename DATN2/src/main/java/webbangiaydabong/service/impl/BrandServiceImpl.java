package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webbangiaydabong.dto.BrandDTO;
import webbangiaydabong.entity.Brand;
import webbangiaydabong.repository.BrandRepository;
import webbangiaydabong.service.BrandService;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository brandRepository;

    @Override
    public Brand findById(Long id) {
        return brandRepository.findById(id).get();
    }

    @Override
    public BrandDTO saveOrUpdate(BrandDTO dto) {
        Brand bran=null;
        if(dto.getId()!=null){
            bran=brandRepository.getOne(dto.getId());
        }
        if(bran==null){
            bran=new Brand();
        }
        if(dto.getName()!=null){
            bran.setName(dto.getName());
        }
       bran= brandRepository.save(bran);
        return new BrandDTO(bran);
    }

    @Override
    public boolean checkName(String name) {
       Integer count=brandRepository.countName(name);
        if(count>0){
            return true;
        }
        return false;
    }

    @Override
    public List<BrandDTO> getAllBrandDtos() {
        return brandRepository.getAllBrand();
    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.getAll();
    }
}
