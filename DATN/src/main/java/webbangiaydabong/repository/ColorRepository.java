package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import webbangiaydabong.entity.Color;

public interface ColorRepository extends JpaRepository<Color, Long>{
    @Query(value = "SELECT count(color.id) FROM color WHERE value=:value",nativeQuery = true)
    Long countvalue(Integer value);

}
