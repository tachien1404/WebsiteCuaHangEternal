package webbangiaydabong.repository;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webbangiaydabong.entity.Brand;
import webbangiaydabong.entity.Category;
import webbangiaydabong.entity.Product;
import webbangiaydabong.entity.Promotion;

import java.util.Date;

public interface PromotionRepository extends JpaRepository<Promotion,Long>{
    @Query("select p FROM Promotion p WHERE" +
            "(lower(p.name)  like '%' ||  lower(:name) || '%' or :name is null)" +
            " and  (p.id  = :id  or :id is null)" +
            " and (p.starttime = :starttime  or :starttime is null)" +
            " and (p.endtime = :endtime  or :endtime is null)" +
            " and  (p.value  = :value  or :value is null)" +
            "and  p.delete = true")
    Page<Promotion> findByKey(
            Pageable pageable,
            @Param("name") String name,
            @Param("id") Long id,
            @Param("starttime") Date starttime,
            @Param("endtime") Date endtime,
            @Param("value") Integer value
    );
}
