package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import webbangiaydabong.entity.ShoeLine;

import java.util.List;
public interface ShoeLineRepository extends JpaRepository<ShoeLine,Long> {
    @Query(value = "select s from ShoeLine s where s.isdelete =true ")
    List<ShoeLine> findAllActive();
}
