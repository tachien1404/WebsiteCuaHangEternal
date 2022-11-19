package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import webbangiaydabong.entity.VanChuyen;
@Repository
public interface VanChuyenRepository extends JpaRepository<VanChuyen, Long>{

}
