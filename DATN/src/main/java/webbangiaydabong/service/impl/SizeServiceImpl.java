package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webbangiaydabong.entity.size;
import webbangiaydabong.repository.SizeRepository;
import webbangiaydabong.service.SizeService;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    SizeRepository repository;
    @Override
    public size findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<size> findAll() {
        return repository.findAll();
    }
}
