package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import webbangiaydabong.entity.ShoeLine;
import webbangiaydabong.repository.ShoeLineRepository;
import webbangiaydabong.repository.SoleRepository;
import webbangiaydabong.service.ShoeLineService;

import java.util.List;

@Service
public class ShoeLineImpl implements ShoeLineService {

    @Autowired
    ShoeLineRepository repository;
    @Override
    public List<ShoeLine> findAllActivce() {
        return repository.findAllActive();
    }
	@Override
	public ShoeLine find(long id) {
		return repository.findById(id).get();
	}
	@Override
	public ShoeLine create(ShoeLine shoeLine) {
		if(repository.existsByName(shoeLine.getName())) return null;
		return  repository.save(shoeLine);
	}
	@Override
	public ShoeLine update(long id, ShoeLine shoeLine) {
		if(repository.existsByName(shoeLine.getName())) return null;
		ShoeLine updated = repository.findByIdAndIsdeleteFalse(id).get();
		updated.setName(shoeLine.getName());
		return repository.save(updated);
	}
	@Override
	public void delete(long id) {
		ShoeLine deletedLine = repository.findByIdAndIsdeleteFalse(id).get();
		deletedLine.setIsdelete(true);
		repository.save(deletedLine);
	}
	@Override
	public Page<ShoeLine> findAll(int page, int shoeLine) {
		page =page <0? 0:page;
		Pageable pageable;
		pageable = PageRequest.of(page, shoeLine,Sort.by("id").descending());
		return repository.findAllByIsdeleteFalse(pageable);
	}
	@Override
	public List<ShoeLine> search(String keyword, String status) {

		if (status.equals("all")) {
			return repository.findByNameLike("%" + keyword + "%");
		} 
		if (status.equals("1")){
			return repository.findByNameLikeAndIsdeleteFalse("%" + keyword + "%");
		}
		return repository.findByNameLikeAndIsdeleteTrue("%" + keyword + "%");
	}
}
