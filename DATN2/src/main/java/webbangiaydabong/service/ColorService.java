package webbangiaydabong.service;

import webbangiaydabong.dto.ColorDTO;
import webbangiaydabong.dto.SizeDTO;
import webbangiaydabong.entity.Color;

import java.util.List;

import org.springframework.data.domain.Page;

public interface ColorService {
    Color findById(Long id);
    List<Color> findAll();
    
    Page<Color> findAlls(int page, int size);
    boolean checkvalue(Integer value);
    Color save(ColorDTO dto);
    Color update(long id,ColorDTO dto);
    void delete(long id);
    List<Color> search(String keyword);
}
