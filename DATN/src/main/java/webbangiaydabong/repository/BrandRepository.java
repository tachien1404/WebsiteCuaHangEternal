package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import webbangiaydabong.dto.BrandDTO;
import webbangiaydabong.dto.OrderDTO;
import webbangiaydabong.entity.Brand;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long>{
    @Query("select new webbangiaydabong.dto.BrandDTO(o) from Brand o ")
    List<BrandDTO> getAllBrand();
}
