package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import webbangiaydabong.entity.Color;
import webbangiaydabong.entity.Product;
import webbangiaydabong.entity.S_C_Details;
import webbangiaydabong.entity.size;
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
    public List<S_C_Details> findBySize(Long productId, Long sizeId) {
        return repo.findBySize(productId, sizeId);
    }

    @Override
    public List<S_C_Details> findByColor(Long productId, Long colorId) {
        return repo.findByColor(productId, colorId);
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
    public Page<S_C_Details> findByKey(Pageable pageable, Long id, Product product, size size, Color mau, Integer quantity, Integer status) {
        return repo.findByKey(pageable, id, product, size, mau, quantity, status);
    }
}
