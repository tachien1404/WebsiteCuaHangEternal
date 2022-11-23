package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webbangiaydabong.entity.OrderDetail;
import webbangiaydabong.repository.OrderDetailRepository;
import webbangiaydabong.service.OrderDetaiService;

import java.util.List;

@Service
public class OrderDetaiServiceImpl implements OrderDetaiService {
    @Autowired
    OrderDetailRepository orderDetailRepo;

    @Override
    public List<OrderDetail> findByIDOrder(Long id) {
        return orderDetailRepo.findOrderDetailByOrder(id);
    }

    @Override
    public List<OrderDetail> saveAll(List<OrderDetail> listDetails) {
        return orderDetailRepo.saveAll(listDetails);
    }

    @Override
    public OrderDetail create(OrderDetail orderDetail) {
        return  orderDetailRepo.save(orderDetail);
    }

    @Override
    public List<OrderDetail> findByOder(Long id) {
        return orderDetailRepo.findOrderDetailByOrder(id);
    }
}
