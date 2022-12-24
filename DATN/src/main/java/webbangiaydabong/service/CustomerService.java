package webbangiaydabong.service;

import webbangiaydabong.dto.CustomerDto;

public interface CustomerService {
    CustomerDto saveOrUpdate(Long id,CustomerDto dto);
    CustomerDto searchName(String name);
    CustomerDto searchSdt(String sdt);
    CustomerDto getbyid(Long id);
}
