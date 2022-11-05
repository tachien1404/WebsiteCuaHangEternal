package webbangiaydabong.service;

import webbangiaydabong.dto.ColorDTO;
import webbangiaydabong.dto.SizeDTO;
import webbangiaydabong.entity.Color;

public interface ColorService {
    Color findById(Long id);
    boolean checkvalue(Integer value);
   ColorDTO save(ColorDTO dto);
}
