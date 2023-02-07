package webbangiaydabong.service;

import webbangiaydabong.entity.Sole;

import java.util.List;

import org.springframework.data.domain.Page;

public interface SoleService {
    List<Sole> findAllActice();
    Sole find(long id);
    Sole create(Sole sole);
    Sole update(long id, Sole sole);
    void delete(long id);
    Page<Sole> findAll(int page, int size);
    List<Sole> search(String keyword, String status);
    List<Sole>getall();
    Sole findName(String name);
}
