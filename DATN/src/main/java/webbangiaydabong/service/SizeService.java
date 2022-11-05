package webbangiaydabong.service;

import webbangiaydabong.dto.SizeDTO;
import webbangiaydabong.entity.size;

public interface SizeService {
    size findById(Long id);
    boolean checkvalue(Integer value);
    SizeDTO save(SizeDTO dto);
}
