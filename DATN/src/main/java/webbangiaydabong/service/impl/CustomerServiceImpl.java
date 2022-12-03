package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webbangiaydabong.dto.CustomerDto;
import webbangiaydabong.entity.Customer;
import webbangiaydabong.repository.CustomerRepository;
import webbangiaydabong.service.CustomerService;

import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CustomerDto saveOrUpdate(Long id, CustomerDto dto) {
        Customer customer=null;
        if(id!=null){
            Optional<Customer> optionalCustomer =customerRepository.findById(id);
            if(optionalCustomer.isPresent()){
                customer=optionalCustomer.get();
            }
        }
        if(customer==null){
            customer=new Customer();
        }
        if(dto.getName()!=null){
            customer.setName(dto.getName());

        }
        if(dto.getSdt()!=null){
            customer.setSdt(dto.getSdt());
        }
        if(dto.getAddress()!=null){
            customer.setAddress(dto.getAddress());
        }
        customer.setActive(true);
        customerRepository.save(customer);
        return new CustomerDto( customerRepository.save(customer));
    }

    @Override
    public CustomerDto searchName(String name) {
        if(name!=null){
            CustomerDto dto=customerRepository.searchName('%'+name+'%');
            return dto;
        }
        return null;
    }
}
