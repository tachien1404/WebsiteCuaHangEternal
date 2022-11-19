package webbangiaydabong.service;

import webbangiaydabong.entity.Cart;

import java.util.List;
import java.util.Optional;

public interface CartService {
    List<Cart> findAllByUserName(String username);
    Cart creat(Cart cart);
    void delete(Long id);
    void deleteAllByUserName(String userName);
    Cart findBySizeColorAndUser(Long idProduct, Long idsize, Long idColor, String userName);

}
