package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import webbangiaydabong.entity.Authority;
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long>{
	
}
