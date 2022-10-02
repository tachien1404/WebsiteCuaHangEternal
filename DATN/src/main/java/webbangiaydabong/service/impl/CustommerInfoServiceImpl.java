package webbangiaydabong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webbangiaydabong.entity.CustommerInfo;
import webbangiaydabong.repository.CustommerInfoRepository;
import webbangiaydabong.service.CustommerInfoServie;
@Service
public class CustommerInfoServiceImpl implements CustommerInfoServie {
	@Autowired
	CustommerInfoRepository Cusrepo;

	@Override
	public List<CustommerInfo> findAll() {
		return Cusrepo.findAll();
	}

	@Override
	public CustommerInfo findById(Integer id) {
		return Cusrepo.findById(id).get();
	}

	@Override
	public CustommerInfo create(CustommerInfo CustomerIn) {
		
		return Cusrepo.save(CustomerIn);
	}

	@Override
	public CustommerInfo update(CustommerInfo CustomerIn) {
		
		return Cusrepo.save(CustomerIn);
	}

}
