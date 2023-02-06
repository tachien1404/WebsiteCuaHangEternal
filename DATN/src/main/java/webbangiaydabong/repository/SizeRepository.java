package webbangiaydabong.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;

import webbangiaydabong.entity.Color;
import webbangiaydabong.entity.Size;

public interface SizeRepository extends JpaRepository<Size, Long>{
	@Query(value = "SELECT count(size.id) FROM size WHERE value=:value",nativeQuery = true)
	Integer countvalue(Integer value);

	Page<Size> findAll(Pageable pageable);

	List<Size> findByValueLike(Integer keyword);

	boolean existsByValue(int value);

	List<Size> findByValueLikeAndIsdeleteFalse(int parseInt);

	List<Size> findByValueLikeAndIsdeleteTrue(int parseInt);

	List<Size> findByIsdeleteFalse();

	List<Size> findByIsdeleteTrue();
}
