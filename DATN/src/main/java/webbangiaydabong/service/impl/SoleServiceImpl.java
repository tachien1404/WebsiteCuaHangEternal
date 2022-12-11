package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webbangiaydabong.entity.Sole;
import webbangiaydabong.repository.SoleRepository;
import webbangiaydabong.service.SoleService;

import java.util.List;
@Service
public class SoleServiceImpl implements SoleService {
    @Autowired
    SoleRepository soleRepository;

    @Override
    public List<Sole> findAllActice() {
        return soleRepository.findAllActive();
    }
}
