package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webbangiaydabong.dto.SizeDTO;
import webbangiaydabong.entity.Size;
import webbangiaydabong.repository.SizeRepository;
import webbangiaydabong.service.SizeService;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {

    
    @Autowired
	SizeRepository repository;

	@Override
	public Size findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Size> findAll() {
		return repository.findAll();
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
		Size size = new Size();
		size.setValue(dto.getValue());
		return repository.save(size);
	}

	@Override
	public Size update(long id, SizeDTO dto) {
		Size size = repository.findById(id).get();
		size.setValue(dto.getValue());
		return repository.save(size);
	}

	@Override
	public void delete(long id) {
		repository.deleteById(id);
	}

}
