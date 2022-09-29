package webbangiaydabong.service;

import java.util.List;

import webbangiaydabong.entity.CustommerInfo;	
public interface CustommerInfoServie {
	List<CustommerInfo> findAll();
	
	CustommerInfo findById(Integer id);
	
	CustommerInfo create(CustommerInfo CustomerIn);
	CustommerInfo update(CustommerInfo CustomerIn);
}
