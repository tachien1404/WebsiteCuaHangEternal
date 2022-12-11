package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import webbangiaydabong.entity.Sole;

import java.util.List;

public interface SoleRepository extends JpaRepository<Sole,Long> {
    @Query(value = "select s from Sole s where s.isdelete =true ")
    List<Sole> findAllActive();
}
