package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webbangiaydabong.dto.SizeDTO;
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

    public boolean checkvalue(Integer value) {
        Long count=repository.countvalue(value);
        if(count>0){
            return true;
        }
        return false;

    }

    @Override
    public SizeDTO save(SizeDTO dto) {
       size size=new size();
        if(dto.getValue()!=null){
            size.setValue(dto.getValue());
        }
        repository.save(size);
        return null;
    }
}
