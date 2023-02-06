package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

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
		if(soleRepository.existsByName(sole.getName())) return null;
		return soleRepository.save(sole);
	}

	@Override
	public Sole update(long id, Sole sole) {
		List<Sole> soles = soleRepository.findAll();
		for(Sole s : soles) {
			if (s.getName().equals(sole.getName()) && s.getId() != sole.getId()) {
				return null;
			}
		}
		Sole updated = soleRepository.findById(id).get();
		updated.setName(sole.getName());
		updated.setIsdelete(sole.isIsdelete());
		return soleRepository.save(updated);
	}

	@Override
	public void delete(long id) {
		Sole updated = soleRepository.findByIdAndIsdeleteFalse(id).get();
		updated.setIsdelete(true);
		soleRepository.save(updated);
	}

	@Override
	public Page<Sole> findAll(int page, int size) {
		page = page <0? 0:page;
		Pageable pageable;
		pageable = PageRequest.of(page,size,Sort.by("id").descending());
		return soleRepository.findAll(pageable);
	}

	@Override
	public List<Sole> search(String keyword, String status) {
		if (status.equals("all")) {
			return soleRepository.findByNameLike("%" + keyword + "%");
		} 
		if (status.equals("1")){
			return soleRepository.findByNameLikeAndIsdeleteFalse("%" + keyword + "%");
		}
		return soleRepository.findByNameLikeAndIsdeleteTrue("%" + keyword + "%");
	}

	@Override
	public List<Sole> getall() {
    	return soleRepository.findAll();

	}
}
