package webbangiaydabong.service;

import webbangiaydabong.dto.SizeDTO;
import webbangiaydabong.entity.Size;

import java.util.List;

public interface SizeService {
    Size findById(Long id);
    List<Size> findAll();
    boolean checkvalue(Integer value);
    Size save(SizeDTO dto);
    Size update(long id, SizeDTO dto);
    void delete(long id);

}
