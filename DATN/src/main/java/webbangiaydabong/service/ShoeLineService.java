package webbangiaydabong.service;

import webbangiaydabong.entity.ShoeLine;


import java.util.List;

import org.springframework.data.domain.Page;

public interface ShoeLineService {
    List<ShoeLine> findAllActivce();
    ShoeLine find(long id);
    ShoeLine create(ShoeLine shoeLine);
    ShoeLine update(long id, ShoeLine shoeLine);
    void delete(long id);
    Page<ShoeLine> findAll(int page, int shoeLine);
    List<ShoeLine> search(String keyword, String status);
    ShoeLine findByName(String name);
    ShoeLine save(ShoeLine shoeLine);
}
