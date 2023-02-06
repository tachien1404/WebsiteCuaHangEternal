package webbangiaydabong.service;

import webbangiaydabong.dto.SizeDTO;
import webbangiaydabong.entity.Size;

import java.util.List;

import org.springframework.data.domain.Page;

public interface SizeService {
	Size findById(Long id);

	List<Size> findAll();

	Page<Size> findAlls(int page, int size);

	boolean checkvalue(Integer value);

	Size save(SizeDTO dto);

	Size update(long id, SizeDTO dto);

	void delete(long id);

	List<Size> search(String keyword, String status);
}
