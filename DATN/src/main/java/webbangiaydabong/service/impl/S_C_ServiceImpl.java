package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import webbangiaydabong.dto.S_C_DetailDTO;
import webbangiaydabong.entity.*;
import webbangiaydabong.repository.S_C_Repository;
import webbangiaydabong.service.S_C_DetailService;

import java.util.List;

@Service
public class S_C_ServiceImpl implements S_C_DetailService {

    @Autowired
    S_C_Repository repo;


    @Override
    public List<S_C_Details> findAll(){
        return repo.findAll();
    }

    @Override
    public S_C_Details findBySizeColor(Long productId, Long sizeId, Long colorId) {
        return repo.findBySizeColor(productId, sizeId, colorId);
    }

    @Override
    public Page<S_C_Details> findConfigProduct(Pageable pageable, Long productId) {
        return repo.findConfigProduct(pageable,productId);
    }

    @Override
    public S_C_Details create(S_C_Details sizeColorDetails) {
       return repo.save(sizeColorDetails);
    }

    @Override
    public S_C_Details update(S_C_Details sizeColorDetails) {
        return repo.save(sizeColorDetails);
    }

    @Override
    public void delete(Long id) {
       repo.deleteById(id);
    }

    @Override
    public Page<S_C_Details> findByKey(Pageable pageable, Long id, Product product, Size size, Color mau, Integer quantity, Integer status) {
        return repo.findByKey(pageable, id, product, size, mau, quantity, status);
    }

    @Override
    public List<S_C_Details> findQuantity(Product product, Size size, Color mau) {
        return repo.findQuantity(product, size, mau);
    }

    @Override
    public List<S_C_DetailDTO> congsl(List<S_C_DetailDTO> dto) {
        for (S_C_DetailDTO x:dto){
            S_C_Details s_c_detail=repo.findById(x.getId()).get();
            s_c_detail.setQuantity(s_c_detail.getQuantity()+ x.getQuantity());
            repo.save(s_c_detail);
        }
        return null;
    }
    @Override
    public List<S_C_DetailDTO> trusl(List<S_C_DetailDTO> dto) {
        for (S_C_DetailDTO x:dto){
            S_C_Details s_c_detail=repo.findById(x.getId()).get();
            s_c_detail.setQuantity(s_c_detail.getQuantity()- x.getQuantity());
            repo.save(s_c_detail);
        }
        return null;
    }
}
