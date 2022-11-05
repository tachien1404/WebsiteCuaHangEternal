package webbangiaydabong.service;

import webbangiaydabong.dto.SizeDTO;
import webbangiaydabong.entity.size;

import java.util.List;

public interface SizeService {
    size findById(Long id);
    List<size> findAll();
    boolean checkvalue(Integer value);
    SizeDTO save(SizeDTO dto);
}
