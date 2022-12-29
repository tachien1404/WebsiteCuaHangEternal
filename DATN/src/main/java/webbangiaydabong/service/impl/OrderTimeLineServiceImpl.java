package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webbangiaydabong.dto.OrderTimeLineDTO;
import webbangiaydabong.entity.Account;
import webbangiaydabong.entity.Order;
import webbangiaydabong.entity.OrderTimeLine;
import webbangiaydabong.repository.AccountRepository;
import webbangiaydabong.repository.OrderRepository;
import webbangiaydabong.repository.OrderTimeLineRepository;
import webbangiaydabong.service.OrderTimeLineService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderTimeLineServiceImpl implements OrderTimeLineService {
    @Autowired
    OrderTimeLineRepository orderTimeLineRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    AccountRepository accountRepository;
    @Override
    public OrderTimeLineDTO save(OrderTimeLineDTO dto) {
        OrderTimeLine entity=null;
        if(dto.getId()!=null){
            Optional<OrderTimeLine>optional=orderTimeLineRepository.findById(dto.getId());
            if(optional.isPresent()){
                entity=optional.get();
            }
        }
        if(entity==null){
            entity=new OrderTimeLine();
        }
        if(dto.getType()!=null){
            entity.setType(dto.getType());
        }
        if(dto.getOrder_id()!=null){
            Order o=orderRepository.findById(dto.getOrder_id()).get();
            entity.setOrder(o);
        }
        if(dto.getAccount_id()!=null){
            Account a=accountRepository.findById(dto.getAccount_id()).get();
            entity.setAccount(a);
        }
        entity.setDescription(dto.getDescription());
        entity=orderTimeLineRepository.save(entity);

        return  new OrderTimeLineDTO(entity);
    }

    @Override
    public List<OrderTimeLineDTO> getBy(Long order_id, Long account_id) {
        List<OrderTimeLineDTO>getByOvsA=orderTimeLineRepository.getByOrderIdAndAccountId(order_id,account_id);
        return getByOvsA;
    }
}
