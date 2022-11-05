package webbangiaydabong.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.query.Param;
import webbangiaydabong.entity.*;

import java.util.List;

public interface S_C_Repository extends JpaRepository<S_C_Details,Long> {

    @Query("select sz FROM S_C_Details sz WHERE sz.product.id =?1 and sz.size.id =?2 and sz.mau.id =?3")
    S_C_Details findBySizeColor(Long product_id, Long size_id, Long color_id);

    @Query("select sz FROM S_C_Details sz WHERE sz.product.id = ?1 and sz.size.id =?2")
    List<S_C_Details> findBySize(Long product_id, Long size_id);

    @Query("select sz FROM S_C_Details sz WHERE sz.product.id = ?1 and sz.mau.id =?2")
    List<S_C_Details> findByColor(Long product_id, Long color_id);

    @Query("select sc FROM S_C_Details sc WHERE" +
            " (sc.id  = :id  or :id is null)" +
            "and   (sc.product  =:product or :product is null)"+
            "and   (sc.size  =:size or :size is null)"+
            "and   (sc.mau  =:mau or :mau is null)"+
            " and  (sc.quantity  = :quantity  or :quantity is null)" +
            " and  (sc.status  = :status  or :status is null)" )

    Page<S_C_Details> findByKey(
            Pageable pageable,
            @Param("id") Long id,
            @Param("product") Product product,
            @Param("size") size size,
            @Param("mau") Color mau,
            @Param("quantity") Integer quantity,
            @Param("status") Integer status
    );

}
