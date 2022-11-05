package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webbangiaydabong.entity.Color;
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
}
