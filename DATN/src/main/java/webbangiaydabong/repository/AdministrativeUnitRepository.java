package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import webbangiaydabong.entity.AdministrativeUnit;

import java.util.List;

@Repository
public interface AdministrativeUnitRepository extends JpaRepository<AdministrativeUnit,Long> {
    @Query("select e from AdministrativeUnit e  where e.code=?1")
    List<AdministrativeUnit> findListByCode(String code);
}
