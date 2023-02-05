package webbangiaydabong.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import webbangiaydabong.entity.ShoeLine;

import java.util.List;
import java.util.Optional;
public interface ShoeLineRepository extends JpaRepository<ShoeLine,Long> {
    @Query(value = "select s from ShoeLine s where s.isdelete =true ")
    List<ShoeLine> findAllActive();
    
    Optional<ShoeLine> findByIdAndIsdeleteFalse(long id);
    
	Page<ShoeLine> findAllByIsdeleteFalse(Pageable pageable);

	List<ShoeLine> findByNameLikeAndIsdeleteFalse(String keyword);
	
	boolean existsByName(String value);
}
