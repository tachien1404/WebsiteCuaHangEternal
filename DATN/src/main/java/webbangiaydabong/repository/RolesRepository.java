package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import webbangiaydabong.entity.Roles;
@Repository
public interface RolesRepository extends JpaRepository<Roles, Long>{
	
}
