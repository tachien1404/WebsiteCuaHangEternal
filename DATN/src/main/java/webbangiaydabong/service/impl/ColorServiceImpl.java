package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import webbangiaydabong.dto.ColorDTO;
import webbangiaydabong.dto.SizeDTO;
import webbangiaydabong.entity.Color;
import webbangiaydabong.entity.Size;
import webbangiaydabong.repository.ColorRepository;
import webbangiaydabong.service.ColorService;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {

	@Autowired
	ColorRepository repository;

	@Override
	public Color findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public Page<Color> findAlls(int page, int size) {
		page = page <0? 0:page;
		Pageable pageable;
		pageable = PageRequest.of(page,size);
		return repository.getPage(pageable);
	}

	@Override
	public boolean checkvalue(Integer value) {
		Integer count = repository.countvalue(value);
		if (count > 0) {
			return true;
		}
		return false;

	}

	@Override
	public Color save(ColorDTO dto) {
		Color color = new Color();
		color.setValue(dto.getValue());
		return repository.save(color);
	}

	@Override
	public Color update(long id, ColorDTO dto) {
		Color color = repository.findById(id).get();
		color.setValue(dto.getValue());
		return repository.save(color);
	}

	@Override
	public void delete(long id) {
		repository.deleteById(id);
	}

	@Override
	public List<Color> search(String keyword) {
		return repository.findByNameLikeOrValueLike(keyword);
	}


    @Override
    public List<Color> findAll() {
        return repository.findAll();
    }

	
}
