package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import webbangiaydabong.entity.Image;
@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{

}
