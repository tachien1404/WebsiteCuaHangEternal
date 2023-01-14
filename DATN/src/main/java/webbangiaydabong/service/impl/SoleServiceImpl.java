package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import webbangiaydabong.entity.Sole;
import webbangiaydabong.repository.SoleRepository;
import webbangiaydabong.service.SoleService;

import java.util.List;
@Service
public class SoleServiceImpl implements SoleService {
    @Autowired
    SoleRepository soleRepository;

    @Override
    public List<Sole> findAllActice() {
        return soleRepository.findAllActive();
    }

	@Override
	public Sole find(long id) {
		return soleRepository.findById(id).get();
	}

	@Override
	public Sole create(Sole sole) {
		return soleRepository.save(sole);
	}

	@Override
	public Sole update(long id, Sole sole) {
		Sole updated = soleRepository.findByIdAndIsDeleteFalse(id).get();
		updated.setName(sole.getName());
		return soleRepository.save(updated);
	}

	@Override
	public void delete(long id) {
		soleRepository.deleteById(id);
	}

	@Override
	public Page<Sole> findAll(int page, int size) {
		page = page <0? 0:page;
		Pageable pageable;
		pageable = PageRequest.of(page,size);
		return soleRepository.getPage(pageable);
	}

	@Override
	public List<Sole> search(String keyword) {
		return soleRepository.findByNameLike(keyword);
	}
}
