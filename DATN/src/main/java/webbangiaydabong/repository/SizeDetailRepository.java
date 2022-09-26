package webbangiaydabong.repository;

import javax.persistence.JoinColumn;

import org.springframework.data.jpa.repository.JpaRepository;

import webbangiaydabong.entity.SizeDetail;

public interface SizeDetailRepository extends JpaRepository<SizeDetail, Long>{

}
