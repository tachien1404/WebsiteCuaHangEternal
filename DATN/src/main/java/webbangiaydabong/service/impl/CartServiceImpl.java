package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webbangiaydabong.entity.Cart;
import webbangiaydabong.repository.CartRepository;
import webbangiaydabong.service.CartService;
import webbangiaydabong.service.S_C_DetailService;

import javax.persistence.Access;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public List<Cart> findAllByUserName(String userName) {
        return cartRepository.findAllByUserName(userName);
    }

    @Override
    public Cart creat(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void delete(Long id) {
       cartRepository.deleteById(id);
    }

    @Override
    public void deleteAllByUserName(String userName) {
      cartRepository.deleteAllByUserName(userName);
    }

    @Override
    public Cart findBySizeColorAndUser(Long idProduct, Long idsize, Long idColor, String userName) {
        return cartRepository.findBySCAndUser(idProduct,idsize,idColor,userName);
    }



}
