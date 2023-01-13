package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Color> findAll() {
        return repository.findAll();
    }
    @Override
    public boolean checkvalue(Integer value) {
        Integer count=repository.countvalue(value);
        if(count>0){
            return true;
        }
        return false;

    }

    @Override
    public ColorDTO save(ColorDTO dto) {
       Color color=new Color();
        if(dto.getValue()!=null){
            color.setValue(dto.getValue());
        }
        repository.save(color);
        return null;
    }
}
