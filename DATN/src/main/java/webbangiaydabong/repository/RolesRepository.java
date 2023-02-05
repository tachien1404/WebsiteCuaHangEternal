package webbangiaydabong.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import webbangiaydabong.entity.Roles;

public interface RolesRepository extends JpaRepository<Roles, Long>{

	Optional<Roles> findByNameLike(String name);
	
}
