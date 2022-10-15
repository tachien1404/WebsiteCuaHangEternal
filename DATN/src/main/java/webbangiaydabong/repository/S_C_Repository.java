package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import webbangiaydabong.entity.S_C_Details;

import java.util.List;

public interface S_C_Repository extends JpaRepository<S_C_Details,Long> {

    @Query("select sz FROM S_C_Details sz WHERE sz.product.id =?1 and sz.size.id =?2 and sz.mau.id =?3")
    S_C_Details findBySizeColor(Long product_id, Long size_id, Long color_id);

    @Query("select sz FROM S_C_Details sz WHERE sz.product.id = ?1 and sz.size.id =?2")
    List<S_C_Details> findBySize(Long product_id, Long size_id);

    @Query("select sz FROM S_C_Details sz WHERE sz.product.id = ?1 and sz.mau.id =?2")
    List<S_C_Details> findByColor(Long product_id, Long color_id);
}
