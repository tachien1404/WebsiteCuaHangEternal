package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import webbangiaydabong.dto.BrandDTO;
import webbangiaydabong.dto.OrderDTO;
import webbangiaydabong.entity.Brand;

import java.util.List;
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    @Query("select new webbangiaydabong.dto.BrandDTO(o) from Brand o ")
    List<BrandDTO> getAllBrand();

    @Query("select b FROM Brand b where b.delete =true ")
    List<Brand> getAll();

    @Query(value = "SELECT COUNT(brand.id) FROM brand WHERE brand.name LIKE :name", nativeQuery = true)
   Integer countName(String name);
}
