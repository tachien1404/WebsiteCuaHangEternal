package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import webbangiaydabong.entity.size;

public interface SizeRepository extends JpaRepository<size, Long>{
@Query(value = "SELECT count(size.id) FROM size WHERE value=:value",nativeQuery = true)
Integer countvalue(Integer value);
}
