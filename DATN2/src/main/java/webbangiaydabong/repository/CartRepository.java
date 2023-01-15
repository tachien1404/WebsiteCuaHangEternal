package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import webbangiaydabong.entity.Cart;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart,Long> {

    List<Cart> findAllByUserName(String userName);

    @Modifying
    @Query("delete from Cart c where c.userName= ?1")
    void deleteAllByUserName(String userName);

    @Query("select c from Cart c where c.product.id =?1 and c.size.id=?2" +
            " and c.mau.id=?3 and c.userName=?4")
    Cart findBySCAndUser(Long idProduct, Long idSize, Long idColor, String userName);

}
