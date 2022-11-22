package webbangiaydabong.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import webbangiaydabong.dto.CustommerInfoDTO;
import webbangiaydabong.entity.Account;
import webbangiaydabong.entity.CustommerInfo;

public interface CustommerInfoServie {
    List<CustommerInfo> findAll();

    CustommerInfo findById(Long id);

    CustommerInfoDTO CRUD(CustommerInfoDTO CustomerIn);

    List<CustommerInfo> findAllByAccount(String userName);

    CustommerInfo create(CustommerInfo custommerInfo);

    void delete(Long id);


    Page<CustommerInfo> findByKey(
            Pageable pageable,
            String name,
            String sdt,
            String address,
            Account account
    );

    List<CustommerInfo> findByActive(String userName);
}
