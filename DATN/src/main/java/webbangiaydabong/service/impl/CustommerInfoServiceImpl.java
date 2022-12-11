package webbangiaydabong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import webbangiaydabong.dto.CustommerInfoDTO;
import webbangiaydabong.entity.Account;
import webbangiaydabong.entity.CustommerInfo;
import webbangiaydabong.repository.AccountRepository;
import webbangiaydabong.repository.CustommerInfoRepository;
import webbangiaydabong.service.CustommerInfoServie;
@Service
public class CustommerInfoServiceImpl implements CustommerInfoServie {
	@Autowired
	CustommerInfoRepository Cusrepo;
	@Autowired
	AccountRepository acountepo;

	@Override
	public List<CustommerInfo> findAll() {
		return Cusrepo.findAll();
	}

	@Override
	public CustommerInfoDTO CRUD(CustommerInfoDTO CustomerIn) {
		CustommerInfo entity = null;
		if (CustomerIn.getId()!=null) {
			entity = Cusrepo.getOne(CustomerIn.getId());
		}if (entity ==null) {
			entity = new CustommerInfo();
		}
		entity.setAddress(CustomerIn.getAddress());
		entity.setName(CustomerIn.getName());
		entity.setSdt(CustomerIn.getSdt());
		
		Account acount = acountepo.getOne(CustomerIn.getAccount_id());
		entity.setAccount(acount);
		
		 Cusrepo.save(entity);
		 return CustomerIn;
	}

	@Override
	public List<CustommerInfo> findAllByAccount(String userName) {
		return Cusrepo.findCustommerInfoByUser(userName);
	}

	@Override
	public CustommerInfo create(CustommerInfo custommerInfo) {
		return Cusrepo.save(custommerInfo);
	}

	@Override
	public void delete(Long id) {
    Cusrepo.deleteById(id);
	}

	@Override
	public Page<CustommerInfo> findByKey(Pageable pageable, String name, String sdt, String address, Account account) {
		return Cusrepo.findByKey(pageable, name, sdt, address, account);
	}

	@Override
	public List<CustommerInfo> findByActive(String userName) {
		return Cusrepo.findByActive(userName);
	}

	@Override
	public CustommerInfo findCustommerDefalut(String userName) {
		return Cusrepo.findCustommerInfoDefaulte(userName);
	}

	@Override
	public CustommerInfo findById(Long id) {
		return Cusrepo.findById(id).get();
	}

}
