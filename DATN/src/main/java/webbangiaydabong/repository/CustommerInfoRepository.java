package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import webbangiaydabong.entity.CustommerInfo;

import java.util.List;

public interface CustommerInfoRepository extends JpaRepository<CustommerInfo, Long>{

    @Query("select c from CustommerInfo c where c.account.userName =?1")
    List<CustommerInfo> findCustommerInfoByUser(String userName);
}
