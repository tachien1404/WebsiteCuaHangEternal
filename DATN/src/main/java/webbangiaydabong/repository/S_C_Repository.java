package webbangiaydabong.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.query.Param;
import webbangiaydabong.dto.ColorDTO;
import webbangiaydabong.dto.SizeDTO;
import webbangiaydabong.entity.*;

import java.util.List;

public interface S_C_Repository extends JpaRepository<S_C_Details,Long> {

    @Query("select sz FROM S_C_Details sz WHERE sz.product.id =?1 and sz.size.id =?2 and sz.mau.id =?3 and sz.status =1 and sz.quantity>0 ")
    S_C_Details findBySizeColor(Long product_id, Long size_id, Long color_id);

    @Query("select sz FROM S_C_Details sz WHERE sz.product.id =?1 and sz.size.id =?2 and sz.mau.id =?3 and sz.status =1 and sz.quantity>0 ")
    S_C_Details findBySizeColor1(Long product_id, Long size_id, Long color_id);
    @Query("select sz FROM S_C_Details sz WHERE sz.product.id = :id and sz.status=1")
    Page<S_C_Details> findConfigProduct(Pageable pageable,@Param("id") Long id);

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
            @Param("size") Size size,
            @Param("mau") Color mau,
            @Param("quantity") Integer quantity,
            @Param("status") Integer status
    );

    @Query("select sc FROM S_C_Details sc WHERE" +
            "(sc.product  =:product or :product is null)"+
            "and   (sc.size  =:size or :size is null)"+
            "and   (sc.mau  =:mau or :mau is null)"+
    "and sc.status = 1")
    List<S_C_Details> findQuantity(  @Param("product") Product product,
                                     @Param("size") Size size,
                                     @Param("mau") Color mau);

	void deleteAllBySizeId(long id);

//querr tuwf p vs c ra s
    @Query("select new webbangiaydabong.dto.SizeDTO(s.size) from S_C_Details s where s.product.id =:product_id and s.mau.id=:mau_id and s.quantity>0")
    List<SizeDTO>getsize(Long product_id,Long mau_id);

    @Query("select sc.size from S_C_Details sc where sc.product.id = ?1 and sc.status = 1 group by sc.size")
    List<Size> sizeAvailable (Long idProduct);

    @Query("select sc.mau from S_C_Details sc where sc.product.id = ?1 and sc.status = 1 group by sc.mau")
    List<Color> colorAvailable (Long idProduct);

    @Query("select new webbangiaydabong.dto.SizeDTO(sc.size) from S_C_Details sc where sc.product.id = ?1 group by sc.size")
    List<SizeDTO> size (Long idProduct);

    @Query("select new webbangiaydabong.dto.ColorDTO(sc.mau) from S_C_Details sc where sc.product.id = ?1 group by sc.mau")
    List<ColorDTO> color (Long idProduct);

}
