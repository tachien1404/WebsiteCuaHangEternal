package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webbangiaydabong.entity.size;
import webbangiaydabong.repository.SizeRepository;
import webbangiaydabong.service.SizeService;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    SizeRepository repository;
    @Override
    public size findById(Long id) {
        return repository.findById(id).get();
    }
}
