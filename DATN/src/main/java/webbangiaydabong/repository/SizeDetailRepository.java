package webbangiaydabong.repository;

import javax.persistence.JoinColumn;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import webbangiaydabong.entity.SizeDetail;
@Repository
public interface SizeDetailRepository extends JpaRepository<SizeDetail, Long>{

}
