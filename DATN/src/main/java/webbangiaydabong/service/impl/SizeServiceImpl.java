package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import webbangiaydabong.dto.SizeDTO;
import webbangiaydabong.entity.Color;
import webbangiaydabong.entity.Size;
import webbangiaydabong.repository.S_C_Repository;
import webbangiaydabong.repository.SizeRepository;
import webbangiaydabong.service.SizeService;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {

	@Autowired
	SizeRepository repository;

	@Autowired
	S_C_Repository s_C_Repository;

	@Override
	public Size findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public Page<Size> findAlls(int page, int size) {
		page = page < 0 ? 0 : page;
		Pageable pageable;
		pageable = PageRequest.of(page, size, Sort.by("id").descending());
		return repository.findAll(pageable);
	}

	public boolean checkvalue(Integer value) {
		Integer count = repository.countvalue(value);
		if (count > 0) {
			return true;
		}
		return false;

	}

	@Override
	public Size save(SizeDTO dto) {
		if (repository.existsByValue(dto.getValue()))
			return null;
		Size size = new Size();
		size.setValue(dto.getValue());
		return repository.save(size);
	}

	@Override
	public Size update(long id, SizeDTO dto) {
		List<Size> sizes = repository.findAll();
		for (Size size : sizes) {
			if (size.getValue().equals(dto.getValue()) && size.getId() != id) {
				return null;
			}
		}
		Size size = repository.findById(id).get();
		size.setValue(dto.getValue());
		size.setIsdelete(dto.isIsdelete());
		return repository.save(size);
	}

	@Override
	public void delete(long id) {
//		s_C_Repository.deleteAllBySizeId(id);
		Size size = repository.findById(id).get();
		size.setIsdelete(true);
		repository.save(size);
	}

	@Override
	public List<Size> search(String keyword, String status) {
		if (status.equals("all")) {
			if (keyword.trim().length() == 0) {
				return repository.findAll();
			}
			return repository.findByValueLike(Integer.parseInt(keyword));
		}
		if (status.equals("1")) {
			if (keyword.trim().length() == 0) {
				return repository.findByIsdeleteFalse();
			}
			return repository.findByValueLikeAndIsdeleteFalse(Integer.parseInt(keyword));
		}
		if (keyword.trim().length() == 0) {
			return repository.findByIsdeleteTrue();
		}
		return repository.findByValueLikeAndIsdeleteTrue(Integer.parseInt(keyword));
	}

	@Override
	public List<Size> findAll() {
		return repository.findAll();
	}

}
