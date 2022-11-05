package webbangiaydabong.service;

import webbangiaydabong.entity.size;

import java.util.List;

public interface SizeService {
    size findById(Long id);
    List<size> findAll();
}
