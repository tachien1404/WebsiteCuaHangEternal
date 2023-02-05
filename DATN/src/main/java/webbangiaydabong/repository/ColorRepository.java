package webbangiaydabong.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import webbangiaydabong.entity.Color;

public interface ColorRepository extends JpaRepository<Color, Long>{
    @Query(value = "SELECT count(color.id) FROM color WHERE value=:value",nativeQuery = true)
    Integer countvalue(Integer value);

	Page<Color> findAll(Pageable pageable);

	List<Color> findByNameLike(String nameKey);
	boolean existsByName(String value);
	boolean existsByValue(String value);
}
