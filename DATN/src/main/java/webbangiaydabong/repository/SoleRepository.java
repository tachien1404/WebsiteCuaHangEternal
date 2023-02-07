package webbangiaydabong.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import webbangiaydabong.entity.Color;
import webbangiaydabong.entity.Sole;

import java.util.List;
import java.util.Optional;

public interface SoleRepository extends JpaRepository<Sole,Long> {
    @Query(value = "select s from Sole s where s.isdelete =true ")
    List<Sole> findAllActive();
    
    Optional<Sole> findByIdAndIsdeleteFalse(long id);

	Page<Sole> findAllByIsdeleteFalse(Pageable pageable);

	List<Sole> findByNameLikeAndIsdeleteFalse(String keyword);
	
	boolean existsByName(String value);

	List<Sole> findByNameLike(String keyword);

	List<Sole> findByNameLikeAndIsdeleteTrue(String string);
	
	Sole findByName(String name);
}
