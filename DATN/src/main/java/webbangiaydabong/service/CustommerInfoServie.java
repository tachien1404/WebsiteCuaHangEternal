package webbangiaydabong.service;

import java.util.List;

import webbangiaydabong.dto.CustommerInfoDTO;
import webbangiaydabong.entity.CustommerInfo;	
public interface CustommerInfoServie {
List<CustommerInfo> findAll();
	
	CustommerInfo findById(Long id);
	CustommerInfoDTO CRUD(CustommerInfoDTO CustomerIn);
	List<CustommerInfo> findAllByAccount(String userName);
	CustommerInfo create(CustommerInfo custommerInfo);
	void delete(Long id);
}
