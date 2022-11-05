package webbangiaydabong.service;

import webbangiaydabong.entity.Color;

import java.util.List;

public interface ColorService {
    Color findById(Long id);
    List<Color> findAll();
}
