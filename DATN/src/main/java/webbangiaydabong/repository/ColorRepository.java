package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import webbangiaydabong.entity.Color;
@Repository
public interface ColorRepository extends JpaRepository<Color, Long>{
    @Query(value = "SELECT count(color.id) FROM color WHERE value=:value",nativeQuery = true)
    Integer countvalue(Integer value);

}
