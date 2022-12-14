package webbangiaydabong.service;

import webbangiaydabong.dto.ColorDTO;
import webbangiaydabong.dto.SizeDTO;
import webbangiaydabong.entity.Color;

import java.util.List;

public interface ColorService {
    Color findById(Long id);
    List<Color> findAll();
    boolean checkvalue(Integer value);
   ColorDTO save(ColorDTO dto);
}
