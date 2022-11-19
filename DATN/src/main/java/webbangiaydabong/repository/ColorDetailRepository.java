package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import webbangiaydabong.entity.ColorDetail;
@Repository
public interface ColorDetailRepository extends JpaRepository<ColorDetail, Long >{


}
