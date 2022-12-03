package webbangiaydabong.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import webbangiaydabong.entity.*;

import java.util.List;

@Repository
public interface CustommerInfoRepository extends JpaRepository<CustommerInfo, Long>{

    @Query("select c from CustommerInfo c where c.account.username =?1")
    List<CustommerInfo> findCustommerInfoByUser(String userName);

    @Query("select c FROM CustommerInfo c WHERE" +
            " (lower(c.name)  like '%' ||  lower(:name) || '%' or :name is null)" +
            " and (c.sdt  like '%' ||  :sdt || '%' or :sdt is null)" +
            "and (lower(c.address)  like '%' ||  lower(:address) || '%' or :address is null)" +
            "and   (c.account  =:account or :account is null)"+
            "and  c.active = true")
    Page<CustommerInfo> findByKey(
            Pageable pageable,
            @Param("name") String name,
            @Param("sdt") String sdt,
            @Param("address") String address,
            @Param("account") Account account
    );

    @Query("select c from CustommerInfo  c where c.account.username =?1 and c.active=true")
    List<CustommerInfo> findByActive(String userName);

    @Query("select c from CustommerInfo  c where c.sdt = ?1")
    CustommerInfo findBySdt(String sdt);

}
