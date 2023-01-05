package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webbangiaydabong.entity.ShoeLine;
import webbangiaydabong.repository.ShoeLineRepository;
import webbangiaydabong.service.ShoeLineService;

import java.util.List;

@Service
public class ShoeLineImpl implements ShoeLineService {

    @Autowired
    ShoeLineRepository repository;
    @Override
    public List<ShoeLine> findAllActivce() {
        return repository.findAllActive();
    }
}
